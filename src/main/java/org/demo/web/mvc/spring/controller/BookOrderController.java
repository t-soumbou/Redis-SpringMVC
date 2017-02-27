package org.demo.web.mvc.spring.controller;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;


//--- Common classes
import org.demo.web.mvc.spring.commons.AbstractController;
import org.demo.web.mvc.spring.commons.FormMode;
import org.demo.web.mvc.spring.commons.Message;
import org.demo.web.mvc.spring.commons.MessageType;

//--- Entities
import org.demo.data.record.BookOrderRecord;
import org.demo.data.record.ShopRecord;
import org.demo.data.record.EmployeeRecord;
import org.demo.data.record.CustomerRecord;

//--- Persistence services 
import org.demo.persistence.BookOrderPersistence;
import org.demo.persistence.ShopPersistence;
import org.demo.persistence.EmployeePersistence;
import org.demo.persistence.CustomerPersistence;

//--- List Items 
import org.demo.data.record.listitem.ShopListItem;
import org.demo.data.record.listitem.EmployeeListItem;
import org.demo.data.record.listitem.CustomerListItem;

/**
 * Spring MVC controller for 'BookOrder' management.
 */
@Controller
@RequestMapping("/bookOrder")
public class BookOrderController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "bookOrder";
	private static final String MAIN_LIST_NAME   = "list";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_FORM   = "bookOrder/form";
	private static final String JSP_LIST   = "bookOrder/list";

	//--- SAVE ACTION ( in the HTML form )
	private static final String SAVE_ACTION_CREATE   = "/bookOrder/create";
	private static final String SAVE_ACTION_UPDATE   = "/bookOrder/update";

	//--- Main entity service
	@Resource
    private BookOrderPersistence bookOrderService; // Injected by Spring
	//--- Other service(s)
	@Resource
    private ShopPersistence shopService; // Injected by Spring
	@Resource
    private EmployeePersistence employeeService; // Injected by Spring
	@Resource
    private CustomerPersistence customerService; // Injected by Spring

	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public BookOrderController() {
		super(BookOrderController.class, MAIN_ENTITY_NAME );
		log("BookOrderController created.");
	}

	//--------------------------------------------------------------------------------------
	// Spring MVC model management
	//--------------------------------------------------------------------------------------

	private List<ShopListItem> getListOfShopItems() {
		List<ShopRecord> list = shopService.findAll();
		List<ShopListItem> items = new LinkedList<ShopListItem>();
		for ( ShopRecord shop : list ) {
			items.add(new ShopListItem( shop ) );
		}
		//model.addAttribute("listOfShopItems", items ) ;
		return items ;
	}

	private List<EmployeeListItem> getListOfEmployeeItems() {
		List<EmployeeRecord> list = employeeService.findAll();
		List<EmployeeListItem> items = new LinkedList<EmployeeListItem>();
		for ( EmployeeRecord employee : list ) {
			items.add(new EmployeeListItem( employee ) );
		}
		//model.addAttribute("listOfEmployeeItems", items ) ;
		return items ;
	}

	private List<CustomerListItem> getListOfCustomerItems() {
		List<CustomerRecord> list = customerService.findAll();
		List<CustomerListItem> items = new LinkedList<CustomerListItem>();
		for ( CustomerRecord customer : list ) {
			items.add(new CustomerListItem( customer ) );
		}
		//model.addAttribute("listOfCustomerItems", items ) ;
		return items ;
	}

	/**
	 * Populates the Spring MVC model with the given entity and eventually other useful data
	 * @param model
	 * @param bookOrder
	 */
	private void populateModel(Model model, BookOrderRecord bookOrder, FormMode formMode) {
		//--- Main entity
		model.addAttribute(MAIN_ENTITY_NAME, bookOrder);
		if ( formMode == FormMode.CREATE ) {
			model.addAttribute(MODE, MODE_CREATE); // The form is in "create" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_CREATE); 			
			//--- Other data useful in this screen in "create" mode (all fields)
		}
		else if ( formMode == FormMode.UPDATE ) {
			model.addAttribute(MODE, MODE_UPDATE); // The form is in "update" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_UPDATE); 			
			//--- Other data useful in this screen in "update" mode (only non-pk fields)
		}
		// populateListOfShopItems(model);
		model.addAttribute("listOfShopItems", getListOfShopItems() ) ;
		// populateListOfEmployeeItems(model);
		model.addAttribute("listOfEmployeeItems", getListOfEmployeeItems() ) ;
		// populateListOfCustomerItems(model);
		model.addAttribute("listOfCustomerItems", getListOfCustomerItems() ) ;
	}

	//--------------------------------------------------------------------------------------
	// Request mapping
	//--------------------------------------------------------------------------------------
	/**
	 * Shows a list with all the occurrences of BookOrder found in the database
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping()
	public String list(Model model) {
		log("Action 'list'");
		List<BookOrderRecord> list = bookOrderService.findAll();
		model.addAttribute(MAIN_LIST_NAME, list);		
		return JSP_LIST;
	}

	/**
	 * Shows a form page in order to create a new BookOrder
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping("/form")
	public String formForCreate(Model model) {
		log("Action 'formForCreate'");
		//--- New record instance (it will be used to initialize the web form fields ) 
		BookOrderRecord bookOrder = new BookOrderRecord();	
		//--- Initialize the instance here if necessary...
		// bookOrder.setXxxx("XX");
		//--- Populates the model with the new instance
		populateModel( model, bookOrder, FormMode.CREATE);
		//--- Redirect to the 'FORM VIEW' ( JSP )
		return JSP_FORM;
	}

	/**
	 * Shows a form page in order to update an existing BookOrder
	 * @param model Spring MVC model
	 * @param id  primary key element
	 * @return
	 */
	@RequestMapping(value = "/form/{id}")
	public String formForUpdate(Model model, @PathVariable("id") Integer id ) {
		log("Action 'formForUpdate'");
		//--- Search the entity by its primary key
		BookOrderRecord bookOrder = bookOrderService.findById(id);
		//--- Populates the model with the instance
		populateModel( model, bookOrder, FormMode.UPDATE);		
		//--- Redirect to the 'FORM VIEW' ( JSP )
		return JSP_FORM;
	}

	/**
	 * 'CREATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param bookOrder  entity to be created
	 * @param bindingResult Spring MVC binding result (to retrieve validation errors)
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/create" ) // GET or POST
	public String create(@Valid @ModelAttribute("bookOrder") BookOrderRecord bookOrder, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'create'");
		try {
			if (!bindingResult.hasErrors()) {
				BookOrderRecord recordCreated = bookOrderService.create(bookOrder); 
				log("BookOrder created : " + recordCreated );
				model.addAttribute(MAIN_ENTITY_NAME, recordCreated);

				//---
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				return redirectToForm(httpServletRequest, bookOrder.getId() );
			} else {
				log("Action 'create' : binding error(s) " );
				logBindingErrors(bindingResult);
				populateModel( model, bookOrder, FormMode.CREATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			log("Action 'create' : Exception - " + e.getMessage() );
			messageHelper.addException(model, "bookOrder.error.create", e);
			populateModel( model, bookOrder, FormMode.CREATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'UPDATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param bookOrder  entity to be updated
	 * @param bindingResult Spring MVC binding result (to retrieve validation errors)
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/update" ) // GET or POST
	public String update(@Valid @ModelAttribute("bookOrder") BookOrderRecord bookOrder, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'update'");
		try {
			if (!bindingResult.hasErrors()) {
				//--- Perform database operations
				boolean updated = bookOrderService.update(bookOrder);
				log("BookOrder updated : result = " + updated );
				model.addAttribute(MAIN_ENTITY_NAME, bookOrder);
				//--- Set the result message
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				log("Action 'update' : update done - redirect");
				return redirectToForm(httpServletRequest, bookOrder.getId());
			} else {
				log("Action 'update' : binding errors");
				logBindingErrors(bindingResult);
				populateModel( model, bookOrder, FormMode.UPDATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			messageHelper.addException(model, "bookOrder.error.update", e);
			log("Action 'update' : Exception - " + e.getMessage() );
			populateModel( model, bookOrder, FormMode.UPDATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'DELETE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param redirectAttributes
	 * @param id  primary key element
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}") // GET or POST
	public String delete(RedirectAttributes redirectAttributes, @PathVariable("id") Integer id) {
		log("Action 'delete'" );
		try {
			boolean deleted = bookOrderService.deleteById( id );
			log("BookOrder deleted. Key : " + toString(id) + " result = " + deleted );
			//--- Set the result message
			messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));	
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "bookOrder.error.delete", e);
		}
		return redirectToList();
	}

}
