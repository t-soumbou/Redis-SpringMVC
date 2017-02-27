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
import org.demo.data.record.AuthorRecord;

//--- Persistence services 
import org.demo.persistence.AuthorPersistence;


/**
 * Spring MVC controller for 'Author' management.
 */
@Controller
@RequestMapping("/author")
public class AuthorController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "author";
	private static final String MAIN_LIST_NAME   = "list";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_FORM   = "author/form";
	private static final String JSP_LIST   = "author/list";

	//--- SAVE ACTION ( in the HTML form )
	private static final String SAVE_ACTION_CREATE   = "/author/create";
	private static final String SAVE_ACTION_UPDATE   = "/author/update";

	//--- Main entity service
	@Resource
    private AuthorPersistence authorService; // Injected by Spring
	//--- Other service(s)

	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public AuthorController() {
		super(AuthorController.class, MAIN_ENTITY_NAME );
		log("AuthorController created.");
	}

	//--------------------------------------------------------------------------------------
	// Spring MVC model management
	//--------------------------------------------------------------------------------------

	/**
	 * Populates the Spring MVC model with the given entity and eventually other useful data
	 * @param model
	 * @param author
	 */
	private void populateModel(Model model, AuthorRecord author, FormMode formMode) {
		//--- Main entity
		model.addAttribute(MAIN_ENTITY_NAME, author);
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
	 * Shows a list with all the occurrences of Author found in the database
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping()
	public String list(Model model) {
		log("Action 'list'");
		List<AuthorRecord> list = authorService.findAll();
		model.addAttribute(MAIN_LIST_NAME, list);		
		return JSP_LIST;
	}

	/**
	 * Shows a form page in order to create a new Author
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping("/form")
	public String formForCreate(Model model) {
		log("Action 'formForCreate'");
		//--- New record instance (it will be used to initialize the web form fields ) 
		AuthorRecord author = new AuthorRecord();	
		//--- Initialize the instance here if necessary...
		// author.setXxxx("XX");
		//--- Populates the model with the new instance
		populateModel( model, author, FormMode.CREATE);
		//--- Redirect to the 'FORM VIEW' ( JSP )
		return JSP_FORM;
	}

	/**
	 * Shows a form page in order to update an existing Author
	 * @param model Spring MVC model
	 * @param id  primary key element
	 * @return
	 */
	@RequestMapping(value = "/form/{id}")
	public String formForUpdate(Model model, @PathVariable("id") Integer id ) {
		log("Action 'formForUpdate'");
		//--- Search the entity by its primary key
		AuthorRecord author = authorService.findById(id);
		//--- Populates the model with the instance
		populateModel( model, author, FormMode.UPDATE);		
		//--- Redirect to the 'FORM VIEW' ( JSP )
		return JSP_FORM;
	}

	/**
	 * 'CREATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param author  entity to be created
	 * @param bindingResult Spring MVC binding result (to retrieve validation errors)
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/create" ) // GET or POST
	public String create(@Valid @ModelAttribute("author") AuthorRecord author, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'create'");
		try {
			if (!bindingResult.hasErrors()) {
				AuthorRecord recordCreated = authorService.create(author); 
				log("Author created : " + recordCreated );
				model.addAttribute(MAIN_ENTITY_NAME, recordCreated);

				//---
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				return redirectToForm(httpServletRequest, author.getId() );
			} else {
				log("Action 'create' : binding error(s) " );
				logBindingErrors(bindingResult);
				populateModel( model, author, FormMode.CREATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			log("Action 'create' : Exception - " + e.getMessage() );
			messageHelper.addException(model, "author.error.create", e);
			populateModel( model, author, FormMode.CREATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'UPDATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param author  entity to be updated
	 * @param bindingResult Spring MVC binding result (to retrieve validation errors)
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/update" ) // GET or POST
	public String update(@Valid @ModelAttribute("author") AuthorRecord author, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'update'");
		try {
			if (!bindingResult.hasErrors()) {
				//--- Perform database operations
				boolean updated = authorService.update(author);
				log("Author updated : result = " + updated );
				model.addAttribute(MAIN_ENTITY_NAME, author);
				//--- Set the result message
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				log("Action 'update' : update done - redirect");
				return redirectToForm(httpServletRequest, author.getId());
			} else {
				log("Action 'update' : binding errors");
				logBindingErrors(bindingResult);
				populateModel( model, author, FormMode.UPDATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			messageHelper.addException(model, "author.error.update", e);
			log("Action 'update' : Exception - " + e.getMessage() );
			populateModel( model, author, FormMode.UPDATE);
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
			boolean deleted = authorService.deleteById( id );
			log("Author deleted. Key : " + toString(id) + " result = " + deleted );
			//--- Set the result message
			messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));	
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "author.error.delete", e);
		}
		return redirectToList();
	}

}