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
import org.demo.data.record.EmployeeRecord;
import org.demo.data.record.ShopRecord;
import org.demo.data.record.WorkgroupRecord;
import org.demo.data.record.BadgeRecord;

//--- Persistence services 
import org.demo.persistence.EmployeePersistence;
import org.demo.persistence.ShopPersistence;
import org.demo.persistence.WorkgroupPersistence;
import org.demo.persistence.BadgePersistence;

//--- List Items 
import org.demo.data.record.listitem.ShopListItem;
import org.demo.data.record.listitem.WorkgroupListItem;
import org.demo.data.record.listitem.BadgeListItem;

/**
 * Spring MVC controller for 'Employee' management.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "employee";
	private static final String MAIN_LIST_NAME   = "list";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_FORM   = "employee/form";
	private static final String JSP_LIST   = "employee/list";

	//--- SAVE ACTION ( in the HTML form )
	private static final String SAVE_ACTION_CREATE   = "/employee/create";
	private static final String SAVE_ACTION_UPDATE   = "/employee/update";

	//--- Main entity service
	@Resource
    private EmployeePersistence employeeService; // Injected by Spring
	//--- Other service(s)
	@Resource
    private ShopPersistence shopService; // Injected by Spring
	@Resource
    private WorkgroupPersistence workgroupService; // Injected by Spring
	@Resource
    private BadgePersistence badgeService; // Injected by Spring

	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public EmployeeController() {
		super(EmployeeController.class, MAIN_ENTITY_NAME );
		log("EmployeeController created.");
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

	private List<WorkgroupListItem> getListOfWorkgroupItems() {
		List<WorkgroupRecord> list = workgroupService.findAll();
		List<WorkgroupListItem> items = new LinkedList<WorkgroupListItem>();
		for ( WorkgroupRecord workgroup : list ) {
			items.add(new WorkgroupListItem( workgroup ) );
		}
		//model.addAttribute("listOfWorkgroupItems", items ) ;
		return items ;
	}

	private List<BadgeListItem> getListOfBadgeItems() {
		List<BadgeRecord> list = badgeService.findAll();
		List<BadgeListItem> items = new LinkedList<BadgeListItem>();
		for ( BadgeRecord badge : list ) {
			items.add(new BadgeListItem( badge ) );
		}
		//model.addAttribute("listOfBadgeItems", items ) ;
		return items ;
	}

	/**
	 * Populates the Spring MVC model with the given entity and eventually other useful data
	 * @param model
	 * @param employee
	 */
	private void populateModel(Model model, EmployeeRecord employee, FormMode formMode) {
		//--- Main entity
		model.addAttribute(MAIN_ENTITY_NAME, employee);
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
		// populateListOfWorkgroupItems(model);
		model.addAttribute("listOfWorkgroupItems", getListOfWorkgroupItems() ) ;
		// populateListOfBadgeItems(model);
		model.addAttribute("listOfBadgeItems", getListOfBadgeItems() ) ;
	}

	//--------------------------------------------------------------------------------------
	// Request mapping
	//--------------------------------------------------------------------------------------
	/**
	 * Shows a list with all the occurrences of Employee found in the database
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping()
	public String list(Model model) {
		log("Action 'list'");
		List<EmployeeRecord> list = employeeService.findAll();
		model.addAttribute(MAIN_LIST_NAME, list);		
		return JSP_LIST;
	}

	/**
	 * Shows a form page in order to create a new Employee
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping("/form")
	public String formForCreate(Model model) {
		log("Action 'formForCreate'");
		//--- New record instance (it will be used to initialize the web form fields ) 
		EmployeeRecord employee = new EmployeeRecord();	
		//--- Initialize the instance here if necessary...
		// employee.setXxxx("XX");
		//--- Populates the model with the new instance
		populateModel( model, employee, FormMode.CREATE);
		//--- Redirect to the 'FORM VIEW' ( JSP )
		return JSP_FORM;
	}

	/**
	 * Shows a form page in order to update an existing Employee
	 * @param model Spring MVC model
	 * @param code  primary key element
	 * @return
	 */
	@RequestMapping(value = "/form/{code}")
	public String formForUpdate(Model model, @PathVariable("code") String code ) {
		log("Action 'formForUpdate'");
		//--- Search the entity by its primary key
		EmployeeRecord employee = employeeService.findById(code);
		//--- Populates the model with the instance
		populateModel( model, employee, FormMode.UPDATE);		
		//--- Redirect to the 'FORM VIEW' ( JSP )
		return JSP_FORM;
	}

	/**
	 * 'CREATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param employee  entity to be created
	 * @param bindingResult Spring MVC binding result (to retrieve validation errors)
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/create" ) // GET or POST
	public String create(@Valid @ModelAttribute("employee") EmployeeRecord employee, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'create'");
		try {
			if (!bindingResult.hasErrors()) {
				EmployeeRecord recordCreated = employeeService.create(employee); 
				log("Employee created : " + recordCreated );
				model.addAttribute(MAIN_ENTITY_NAME, recordCreated);

				//---
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				return redirectToForm(httpServletRequest, employee.getCode() );
			} else {
				log("Action 'create' : binding error(s) " );
				logBindingErrors(bindingResult);
				populateModel( model, employee, FormMode.CREATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			log("Action 'create' : Exception - " + e.getMessage() );
			messageHelper.addException(model, "employee.error.create", e);
			populateModel( model, employee, FormMode.CREATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'UPDATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param employee  entity to be updated
	 * @param bindingResult Spring MVC binding result (to retrieve validation errors)
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/update" ) // GET or POST
	public String update(@Valid @ModelAttribute("employee") EmployeeRecord employee, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'update'");
		try {
			if (!bindingResult.hasErrors()) {
				//--- Perform database operations
				boolean updated = employeeService.update(employee);
				log("Employee updated : result = " + updated );
				model.addAttribute(MAIN_ENTITY_NAME, employee);
				//--- Set the result message
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				log("Action 'update' : update done - redirect");
				return redirectToForm(httpServletRequest, employee.getCode());
			} else {
				log("Action 'update' : binding errors");
				logBindingErrors(bindingResult);
				populateModel( model, employee, FormMode.UPDATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			messageHelper.addException(model, "employee.error.update", e);
			log("Action 'update' : Exception - " + e.getMessage() );
			populateModel( model, employee, FormMode.UPDATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'DELETE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param redirectAttributes
	 * @param code  primary key element
	 * @return
	 */
	@RequestMapping(value = "/delete/{code}") // GET or POST
	public String delete(RedirectAttributes redirectAttributes, @PathVariable("code") String code) {
		log("Action 'delete'" );
		try {
			boolean deleted = employeeService.deleteById( code );
			log("Employee deleted. Key : " + toString(code) + " result = " + deleted );
			//--- Set the result message
			messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));	
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "employee.error.delete", e);
		}
		return redirectToList();
	}

}
