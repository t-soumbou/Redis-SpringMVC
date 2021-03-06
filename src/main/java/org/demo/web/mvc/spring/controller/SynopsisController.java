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
import org.demo.data.record.SynopsisRecord;
import org.demo.data.record.BookRecord;

//--- Persistence services 
import org.demo.persistence.SynopsisPersistence;
import org.demo.persistence.BookPersistence;

//--- List Items 
import org.demo.data.record.listitem.BookListItem;

/**
 * Spring MVC controller for 'Synopsis' management.
 */
@Controller
@RequestMapping("/synopsis")
public class SynopsisController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "synopsis";
	private static final String MAIN_LIST_NAME   = "list";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_FORM   = "synopsis/form";
	private static final String JSP_LIST   = "synopsis/list";

	//--- SAVE ACTION ( in the HTML form )
	private static final String SAVE_ACTION_CREATE   = "/synopsis/create";
	private static final String SAVE_ACTION_UPDATE   = "/synopsis/update";

	//--- Main entity service
	@Resource
    private SynopsisPersistence synopsisService; // Injected by Spring
	//--- Other service(s)
	@Resource
    private BookPersistence bookService; // Injected by Spring

	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public SynopsisController() {
		super(SynopsisController.class, MAIN_ENTITY_NAME );
		log("SynopsisController created.");
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

	/**
	 * Populates the Spring MVC model with the given entity and eventually other useful data
	 * @param model
	 * @param synopsis
	 */
	private void populateModel(Model model, SynopsisRecord synopsis, FormMode formMode) {
		//--- Main entity
		model.addAttribute(MAIN_ENTITY_NAME, synopsis);
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
	}

	//--------------------------------------------------------------------------------------
	// Request mapping
	//--------------------------------------------------------------------------------------
	/**
	 * Shows a list with all the occurrences of Synopsis found in the database
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping()
	public String list(Model model) {
		log("Action 'list'");
		List<SynopsisRecord> list = synopsisService.findAll();
		model.addAttribute(MAIN_LIST_NAME, list);		
		return JSP_LIST;
	}

	/**
	 * Shows a form page in order to create a new Synopsis
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping("/form")
	public String formForCreate(Model model) {
		log("Action 'formForCreate'");
		//--- New record instance (it will be used to initialize the web form fields ) 
		SynopsisRecord synopsis = new SynopsisRecord();	
		//--- Initialize the instance here if necessary...
		// synopsis.setXxxx("XX");
		//--- Populates the model with the new instance
		populateModel( model, synopsis, FormMode.CREATE);
		//--- Redirect to the 'FORM VIEW' ( JSP )
		return JSP_FORM;
	}

	/**
	 * Shows a form page in order to update an existing Synopsis
	 * @param model Spring MVC model
	 * @param bookId  primary key element
	 * @return
	 */
	@RequestMapping(value = "/form/{bookId}")
	public String formForUpdate(Model model, @PathVariable("bookId") Integer bookId ) {
		log("Action 'formForUpdate'");
		//--- Search the entity by its primary key
		SynopsisRecord synopsis = synopsisService.findById(bookId);
		//--- Populates the model with the instance
		populateModel( model, synopsis, FormMode.UPDATE);		
		//--- Redirect to the 'FORM VIEW' ( JSP )
		return JSP_FORM;
	}

	/**
	 * 'CREATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param synopsis  entity to be created
	 * @param bindingResult Spring MVC binding result (to retrieve validation errors)
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/create" ) // GET or POST
	public String create(@Valid @ModelAttribute("synopsis") SynopsisRecord synopsis, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'create'");
		try {
			if (!bindingResult.hasErrors()) {
				SynopsisRecord recordCreated = synopsisService.create(synopsis); 
				log("Synopsis created : " + recordCreated );
				model.addAttribute(MAIN_ENTITY_NAME, recordCreated);

				//---
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				return redirectToForm(httpServletRequest, synopsis.getBookId() );
			} else {
				log("Action 'create' : binding error(s) " );
				logBindingErrors(bindingResult);
				populateModel( model, synopsis, FormMode.CREATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			log("Action 'create' : Exception - " + e.getMessage() );
			messageHelper.addException(model, "synopsis.error.create", e);
			populateModel( model, synopsis, FormMode.CREATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'UPDATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param synopsis  entity to be updated
	 * @param bindingResult Spring MVC binding result (to retrieve validation errors)
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/update" ) // GET or POST
	public String update(@Valid @ModelAttribute("synopsis") SynopsisRecord synopsis, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'update'");
		try {
			if (!bindingResult.hasErrors()) {
				//--- Perform database operations
				boolean updated = synopsisService.update(synopsis);
				log("Synopsis updated : result = " + updated );
				model.addAttribute(MAIN_ENTITY_NAME, synopsis);
				//--- Set the result message
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				log("Action 'update' : update done - redirect");
				return redirectToForm(httpServletRequest, synopsis.getBookId());
			} else {
				log("Action 'update' : binding errors");
				logBindingErrors(bindingResult);
				populateModel( model, synopsis, FormMode.UPDATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			messageHelper.addException(model, "synopsis.error.update", e);
			log("Action 'update' : Exception - " + e.getMessage() );
			populateModel( model, synopsis, FormMode.UPDATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'DELETE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param redirectAttributes
	 * @param bookId  primary key element
	 * @return
	 */
	@RequestMapping(value = "/delete/{bookId}") // GET or POST
	public String delete(RedirectAttributes redirectAttributes, @PathVariable("bookId") Integer bookId) {
		log("Action 'delete'" );
		try {
			boolean deleted = synopsisService.deleteById( bookId );
			log("Synopsis deleted. Key : " + toString(bookId) + " result = " + deleted );
			//--- Set the result message
			messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));	
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "synopsis.error.delete", e);
		}
		return redirectToList();
	}

}
