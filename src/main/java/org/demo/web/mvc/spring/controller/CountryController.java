package org.demo.web.mvc.spring.controller;

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
import org.demo.data.record.CountryRecord;

//--- Persistence services 
import org.demo.persistence.CountryPersistence;


/**
 * Spring MVC controller for 'Country' management.
 */
@Controller
@RequestMapping("/country")
public class CountryController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "country";
	private static final String MAIN_LIST_NAME   = "list";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_FORM   = "country/form";
	private static final String JSP_LIST   = "country/list";

	//--- SAVE ACTION ( in the HTML form )
	private static final String SAVE_ACTION_CREATE   = "/country/create";
	private static final String SAVE_ACTION_UPDATE   = "/country/update";

	//--- Main entity service
	@Resource
    private CountryPersistence countryService; // Injected by Spring
	//--- Other service(s)

	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public CountryController() {
		super(CountryController.class, MAIN_ENTITY_NAME );
		log("CountryController created.");
	}

	//--------------------------------------------------------------------------------------
	// Spring MVC model management
	//--------------------------------------------------------------------------------------

	/**
	 * Populates the Spring MVC model with the given entity and eventually other useful data
	 * @param model
	 * @param country
	 */
	private void populateModel(Model model, CountryRecord country, FormMode formMode) {
		//--- Main entity
		model.addAttribute(MAIN_ENTITY_NAME, country);
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
	}

	//--------------------------------------------------------------------------------------
	// Request mapping
	//--------------------------------------------------------------------------------------
	/**
	 * Shows a list with all the occurrences of Country found in the database
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping()
	public String list(Model model) {
		log("Action 'list'");
		List<CountryRecord> list = countryService.findAll();
		model.addAttribute(MAIN_LIST_NAME, list);		
		return JSP_LIST;
	}

	/**
	 * Shows a form page in order to create a new Country
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping("/form")
	public String formForCreate(Model model) {
		log("Action 'formForCreate'");
		//--- New record instance (it will be used to initialize the web form fields ) 
		CountryRecord country = new CountryRecord();	
		//--- Initialize the instance here if necessary...
		// country.setXxxx("XX");
		//--- Populates the model with the new instance
		populateModel( model, country, FormMode.CREATE);
		//--- Redirect to the 'FORM VIEW' ( JSP )
		return JSP_FORM;
	}

	/**
	 * Shows a form page in order to update an existing Country
	 * @param model Spring MVC model
	 * @param code  primary key element
	 * @return
	 */
	@RequestMapping(value = "/form/{code}")
	public String formForUpdate(Model model, @PathVariable("code") String code ) {
		log("Action 'formForUpdate'");
		//--- Search the entity by its primary key
		CountryRecord country = countryService.findById(code);
		//--- Populates the model with the instance
		populateModel( model, country, FormMode.UPDATE);		
		//--- Redirect to the 'FORM VIEW' ( JSP )
		return JSP_FORM;
	}

	/**
	 * 'CREATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param country  entity to be created
	 * @param bindingResult Spring MVC binding result (to retrieve validation errors)
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/create" ) // GET or POST
	public String create(@Valid @ModelAttribute("country") CountryRecord country, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'create'");
		try {
			if (!bindingResult.hasErrors()) {
				CountryRecord recordCreated = countryService.create(country); 
				log("Country created : " + recordCreated );
				model.addAttribute(MAIN_ENTITY_NAME, recordCreated);

				//---
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				return redirectToForm(httpServletRequest, country.getCode() );
			} else {
				log("Action 'create' : binding error(s) " );
				logBindingErrors(bindingResult);
				populateModel( model, country, FormMode.CREATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			log("Action 'create' : Exception - " + e.getMessage() );
			messageHelper.addException(model, "country.error.create", e);
			populateModel( model, country, FormMode.CREATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'UPDATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param country  entity to be updated
	 * @param bindingResult Spring MVC binding result (to retrieve validation errors)
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/update" ) // GET or POST
	public String update(@Valid @ModelAttribute("country") CountryRecord country, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'update'");
		try {
			if (!bindingResult.hasErrors()) {
				//--- Perform database operations
				boolean updated = countryService.update(country);
				log("Country updated : result = " + updated );
				model.addAttribute(MAIN_ENTITY_NAME, country);
				//--- Set the result message
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				log("Action 'update' : update done - redirect");
				return redirectToForm(httpServletRequest, country.getCode());
			} else {
				log("Action 'update' : binding errors");
				logBindingErrors(bindingResult);
				populateModel( model, country, FormMode.UPDATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			messageHelper.addException(model, "country.error.update", e);
			log("Action 'update' : Exception - " + e.getMessage() );
			populateModel( model, country, FormMode.UPDATE);
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
			boolean deleted = countryService.deleteById( code );
			log("Country deleted. Key : " + toString(code) + " result = " + deleted );
			//--- Set the result message
			messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));	
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "country.error.delete", e);
		}
		return redirectToList();
	}

}
