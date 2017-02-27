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
import org.demo.data.record.PublisherRecord;
import org.demo.data.record.CountryRecord;

//--- Persistence services 
import org.demo.persistence.PublisherPersistence;
import org.demo.persistence.CountryPersistence;

//--- List Items 
import org.demo.data.record.listitem.CountryListItem;

/**
 * Spring MVC controller for 'Publisher' management.
 */
@Controller
@RequestMapping("/publisher")
public class PublisherController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "publisher";
	private static final String MAIN_LIST_NAME   = "list";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_FORM   = "publisher/form";
	private static final String JSP_LIST   = "publisher/list";

	//--- SAVE ACTION ( in the HTML form )
	private static final String SAVE_ACTION_CREATE   = "/publisher/create";
	private static final String SAVE_ACTION_UPDATE   = "/publisher/update";

	//--- Main entity service
	@Resource
    private PublisherPersistence publisherService; // Injected by Spring
	//--- Other service(s)
	@Resource
    private CountryPersistence countryService; // Injected by Spring

	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public PublisherController() {
		super(PublisherController.class, MAIN_ENTITY_NAME );
		log("PublisherController created.");
	}

	//--------------------------------------------------------------------------------------
	// Spring MVC model management
	//--------------------------------------------------------------------------------------

	private List<CountryListItem> getListOfCountryItems() {
		List<CountryRecord> list = countryService.findAll();
		List<CountryListItem> items = new LinkedList<CountryListItem>();
		for ( CountryRecord country : list ) {
			items.add(new CountryListItem( country ) );
		}
		//model.addAttribute("listOfCountryItems", items ) ;
		return items ;
	}

	/**
	 * Populates the Spring MVC model with the given entity and eventually other useful data
	 * @param model
	 * @param publisher
	 */
	private void populateModel(Model model, PublisherRecord publisher, FormMode formMode) {
		//--- Main entity
		model.addAttribute(MAIN_ENTITY_NAME, publisher);
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
		// populateListOfCountryItems(model);
		model.addAttribute("listOfCountryItems", getListOfCountryItems() ) ;
	}

	//--------------------------------------------------------------------------------------
	// Request mapping
	//--------------------------------------------------------------------------------------
	/**
	 * Shows a list with all the occurrences of Publisher found in the database
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping()
	public String list(Model model) {
		log("Action 'list'");
		List<PublisherRecord> list = publisherService.findAll();
		model.addAttribute(MAIN_LIST_NAME, list);		
		return JSP_LIST;
	}

	/**
	 * Shows a form page in order to create a new Publisher
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping("/form")
	public String formForCreate(Model model) {
		log("Action 'formForCreate'");
		//--- New record instance (it will be used to initialize the web form fields ) 
		PublisherRecord publisher = new PublisherRecord();	
		//--- Initialize the instance here if necessary...
		// publisher.setXxxx("XX");
		//--- Populates the model with the new instance
		populateModel( model, publisher, FormMode.CREATE);
		//--- Redirect to the 'FORM VIEW' ( JSP )
		return JSP_FORM;
	}

	/**
	 * Shows a form page in order to update an existing Publisher
	 * @param model Spring MVC model
	 * @param code  primary key element
	 * @return
	 */
	@RequestMapping(value = "/form/{code}")
	public String formForUpdate(Model model, @PathVariable("code") Integer code ) {
		log("Action 'formForUpdate'");
		//--- Search the entity by its primary key
		PublisherRecord publisher = publisherService.findById(code);
		//--- Populates the model with the instance
		populateModel( model, publisher, FormMode.UPDATE);		
		//--- Redirect to the 'FORM VIEW' ( JSP )
		return JSP_FORM;
	}

	/**
	 * 'CREATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param publisher  entity to be created
	 * @param bindingResult Spring MVC binding result (to retrieve validation errors)
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/create" ) // GET or POST
	public String create(@Valid @ModelAttribute("publisher") PublisherRecord publisher, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'create'");
		try {
			if (!bindingResult.hasErrors()) {
				PublisherRecord recordCreated = publisherService.create(publisher); 
				log("Publisher created : " + recordCreated );
				model.addAttribute(MAIN_ENTITY_NAME, recordCreated);

				//---
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				return redirectToForm(httpServletRequest, publisher.getCode() );
			} else {
				log("Action 'create' : binding error(s) " );
				logBindingErrors(bindingResult);
				populateModel( model, publisher, FormMode.CREATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			log("Action 'create' : Exception - " + e.getMessage() );
			messageHelper.addException(model, "publisher.error.create", e);
			populateModel( model, publisher, FormMode.CREATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'UPDATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param publisher  entity to be updated
	 * @param bindingResult Spring MVC binding result (to retrieve validation errors)
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/update" ) // GET or POST
	public String update(@Valid @ModelAttribute("publisher") PublisherRecord publisher, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'update'");
		try {
			if (!bindingResult.hasErrors()) {
				//--- Perform database operations
				boolean updated = publisherService.update(publisher);
				log("Publisher updated : result = " + updated );
				model.addAttribute(MAIN_ENTITY_NAME, publisher);
				//--- Set the result message
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				log("Action 'update' : update done - redirect");
				return redirectToForm(httpServletRequest, publisher.getCode());
			} else {
				log("Action 'update' : binding errors");
				logBindingErrors(bindingResult);
				populateModel( model, publisher, FormMode.UPDATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			messageHelper.addException(model, "publisher.error.update", e);
			log("Action 'update' : Exception - " + e.getMessage() );
			populateModel( model, publisher, FormMode.UPDATE);
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
	public String delete(RedirectAttributes redirectAttributes, @PathVariable("code") Integer code) {
		log("Action 'delete'" );
		try {
			boolean deleted = publisherService.deleteById( code );
			log("Publisher deleted. Key : " + toString(code) + " result = " + deleted );
			//--- Set the result message
			messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));	
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "publisher.error.delete", e);
		}
		return redirectToList();
	}

}
