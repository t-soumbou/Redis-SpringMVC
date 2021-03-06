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
import org.demo.data.record.ReviewRecord;
import org.demo.data.record.BookRecord;
import org.demo.data.record.CustomerRecord;

//--- Persistence services 
import org.demo.persistence.ReviewPersistence;
import org.demo.persistence.BookPersistence;
import org.demo.persistence.CustomerPersistence;

//--- List Items 
import org.demo.data.record.listitem.BookListItem;
import org.demo.data.record.listitem.CustomerListItem;

/**
 * Spring MVC controller for 'Review' management.
 */
@Controller
@RequestMapping("/review")
public class ReviewController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "review";
	private static final String MAIN_LIST_NAME   = "list";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_FORM   = "review/form";
	private static final String JSP_LIST   = "review/list";

	//--- SAVE ACTION ( in the HTML form )
	private static final String SAVE_ACTION_CREATE   = "/review/create";
	private static final String SAVE_ACTION_UPDATE   = "/review/update";

	//--- Main entity service
	@Resource
    private ReviewPersistence reviewService; // Injected by Spring
	//--- Other service(s)
	@Resource
    private BookPersistence bookService; // Injected by Spring
	@Resource
    private CustomerPersistence customerService; // Injected by Spring

	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public ReviewController() {
		super(ReviewController.class, MAIN_ENTITY_NAME );
		log("ReviewController created.");
	}

	//--------------------------------------------------------------------------------------
	// Spring MVC model management
	//--------------------------------------------------------------------------------------

	private List<BookListItem> getListOfBookItems() {
		List<BookRecord> list = bookService.findAll();
		List<BookListItem> items = new LinkedList<BookListItem>();
		for ( BookRecord book : list ) {
			items.add(new BookListItem( book ) );
		}
		//model.addAttribute("listOfBookItems", items ) ;
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
	 * @param review
	 */
	private void populateModel(Model model, ReviewRecord review, FormMode formMode) {
		//--- Main entity
		model.addAttribute(MAIN_ENTITY_NAME, review);
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
		// populateListOfBookItems(model);
		model.addAttribute("listOfBookItems", getListOfBookItems() ) ;
		// populateListOfCustomerItems(model);
		model.addAttribute("listOfCustomerItems", getListOfCustomerItems() ) ;
	}

	//--------------------------------------------------------------------------------------
	// Request mapping
	//--------------------------------------------------------------------------------------
	/**
	 * Shows a list with all the occurrences of Review found in the database
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping()
	public String list(Model model) {
		log("Action 'list'");
		List<ReviewRecord> list = reviewService.findAll();
		model.addAttribute(MAIN_LIST_NAME, list);		
		return JSP_LIST;
	}

	/**
	 * Shows a form page in order to create a new Review
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping("/form")
	public String formForCreate(Model model) {
		log("Action 'formForCreate'");
		//--- New record instance (it will be used to initialize the web form fields ) 
		ReviewRecord review = new ReviewRecord();	
		//--- Initialize the instance here if necessary...
		// review.setXxxx("XX");
		//--- Populates the model with the new instance
		populateModel( model, review, FormMode.CREATE);
		//--- Redirect to the 'FORM VIEW' ( JSP )
		return JSP_FORM;
	}

	/**
	 * Shows a form page in order to update an existing Review
	 * @param model Spring MVC model
	 * @param customerCode  primary key element
	 * @param bookId  primary key element
	 * @return
	 */
	@RequestMapping(value = "/form/{customerCode}/{bookId}")
	public String formForUpdate(Model model, @PathVariable("customerCode") String customerCode, @PathVariable("bookId") Integer bookId ) {
		log("Action 'formForUpdate'");
		//--- Search the entity by its primary key
		ReviewRecord review = reviewService.findById(customerCode, bookId);
		//--- Populates the model with the instance
		populateModel( model, review, FormMode.UPDATE);		
		//--- Redirect to the 'FORM VIEW' ( JSP )
		return JSP_FORM;
	}

	/**
	 * 'CREATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param review  entity to be created
	 * @param bindingResult Spring MVC binding result (to retrieve validation errors)
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/create" ) // GET or POST
	public String create(@Valid @ModelAttribute("review") ReviewRecord review, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'create'");
		try {
			if (!bindingResult.hasErrors()) {
				ReviewRecord recordCreated = reviewService.create(review); 
				log("Review created : " + recordCreated );
				model.addAttribute(MAIN_ENTITY_NAME, recordCreated);

				//---
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				return redirectToForm(httpServletRequest, review.getCustomerCode(), review.getBookId() );
			} else {
				log("Action 'create' : binding error(s) " );
				logBindingErrors(bindingResult);
				populateModel( model, review, FormMode.CREATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			log("Action 'create' : Exception - " + e.getMessage() );
			messageHelper.addException(model, "review.error.create", e);
			populateModel( model, review, FormMode.CREATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'UPDATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param review  entity to be updated
	 * @param bindingResult Spring MVC binding result (to retrieve validation errors)
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/update" ) // GET or POST
	public String update(@Valid @ModelAttribute("review") ReviewRecord review, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'update'");
		try {
			if (!bindingResult.hasErrors()) {
				//--- Perform database operations
				boolean updated = reviewService.update(review);
				log("Review updated : result = " + updated );
				model.addAttribute(MAIN_ENTITY_NAME, review);
				//--- Set the result message
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				log("Action 'update' : update done - redirect");
				return redirectToForm(httpServletRequest, review.getCustomerCode(), review.getBookId());
			} else {
				log("Action 'update' : binding errors");
				logBindingErrors(bindingResult);
				populateModel( model, review, FormMode.UPDATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			messageHelper.addException(model, "review.error.update", e);
			log("Action 'update' : Exception - " + e.getMessage() );
			populateModel( model, review, FormMode.UPDATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'DELETE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param redirectAttributes
	 * @param customerCode  primary key element
	 * @param bookId  primary key element
	 * @return
	 */
	@RequestMapping(value = "/delete/{customerCode}/{bookId}") // GET or POST
	public String delete(RedirectAttributes redirectAttributes, @PathVariable("customerCode") String customerCode, @PathVariable("bookId") Integer bookId) {
		log("Action 'delete'" );
		try {
			boolean deleted = reviewService.deleteById( customerCode, bookId );
			log("Review deleted. Key : " + toString(customerCode, bookId) + " result = " + deleted );
			//--- Set the result message
			messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));	
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "review.error.delete", e);
		}
		return redirectToList();
	}

}
