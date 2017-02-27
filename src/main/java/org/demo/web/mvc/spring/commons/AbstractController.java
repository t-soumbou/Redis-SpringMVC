package org.demo.web.mvc.spring.commons;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

public abstract class AbstractController {
	
	protected static final String MODE        = "mode";
	protected static final String MODE_CREATE = "create";
	protected static final String MODE_UPDATE = "update";
	
	protected static final String SAVE_ACTION = "saveAction" ;
	
	private static final String DATE_FORMAT_PATTERN = "date_format_pattern";

	private final String entityName ;
	private final Logger logger ;

//	@Resource
//	protected ControllerHelper controllerHelper;
	@Resource
	protected MessageHelper messageHelper;
	@Resource
	private MessageSource messageSource;
	
	private Map<Locale, CustomDateEditor> customDateEditorByLocales = new HashMap<Locale, CustomDateEditor>();
	
	public AbstractController(Class<? extends AbstractController> controllerClass, String entityName ) {
		super();
		this.entityName = entityName ;
		this.logger = LoggerFactory.getLogger(controllerClass) ;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) {
		Locale locale = RequestContextUtils.getLocale(request);
		binder.registerCustomEditor(Date.class, getCustomDateEditor(locale));
	}
	
	private CustomDateEditor getCustomDateEditor(Locale locale) {
		CustomDateEditor customDateEditor = customDateEditorByLocales.get(locale);
		if(customDateEditor == null) {
			String dateFormatPattern = messageSource.getMessage(DATE_FORMAT_PATTERN, null, locale);
			SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
			customDateEditor = new CustomDateEditor(dateFormat, true);
			customDateEditorByLocales.put(locale, customDateEditor);
		}
		return customDateEditor;
	}

	//-----------------------------------------------------------------------------------------------
	// LOG
	//-----------------------------------------------------------------------------------------------

	protected void log(String msg) {
		logger.info(msg);
	}

	protected void logContent(Model model) {
		Map<String,Object> map = model.asMap();
		logger.info("Model content (size = " + map.size() + ") : ");
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			logger.info(" . '" + entry.getKey() + "' : " + entry.getValue() );
		}
	}
	
	protected void logSessionContent(HttpSession session) {
		Enumeration<String> enumNames = session.getAttributeNames();
		List<String> names = Collections.list(enumNames);
		logger.info("Session content (size = " + names.size() + ") : ");
		for ( String name : names ) {
			logger.info(" . '" + name + "' : " + session.getAttribute(name) );
		}
	}
	
	protected void logBindingErrors(BindingResult bindingResult) {
		log( "BindingResult : " + bindingResult.getErrorCount() + " error(s)" );
		if ( bindingResult.hasFieldErrors() ) {
			log( bindingResult.getFieldErrorCount() + " field error(s) : " );
			for ( FieldError error : bindingResult.getFieldErrors() ) {
				log(" . " + error.getObjectName() + "." +  error.getField() + " - " + error.getCode() + " : " + error.getDefaultMessage() );
			}
		}
		if ( bindingResult.hasGlobalErrors() ) {
			log( bindingResult.getGlobalErrorCount() + " global error(s) : " );
			for ( ObjectError error : bindingResult.getGlobalErrors() ) {
				log(" . " + error.toString());
			}
		}
	}

	//-----------------------------------------------------------------------------------------------

	private static final String URI_SEPARATOR = "/";
	
	
	/**
	 * Returns "redirect:/entityName" 
	 * @return
	 */
	protected String redirectToList() {
		return "redirect:/" + this.entityName ;
	}

	/**
	 * Returns "redirect:/entityName/form/id1/id2/..." 
	 * @param httpServletRequest
	 * @param idParts
	 * @return
	 */
	protected String redirectToForm(HttpServletRequest httpServletRequest, Object... idParts) {
		return "redirect:" + getFormURL(httpServletRequest, idParts);
	}

	/**
	 * Returns "/entityName/form/id1/id2/..." 
	 * @param httpServletRequest
	 * @param idParts
	 * @return
	 */
	protected String getFormURL(HttpServletRequest httpServletRequest, Object... idParts) {
		return "/" + this.entityName + "/form/" + encodeUrlPathSegments(httpServletRequest, idParts );
	}

	private String encodeUrlPathSegments(HttpServletRequest httpServletRequest, Object... pathSegments) {
		//--- get character encoding
		String characterEncoding = httpServletRequest.getCharacterEncoding();
		if (characterEncoding == null) {
			characterEncoding = WebUtils.DEFAULT_CHARACTER_ENCODING;
		}

		//--- encode N segments
		StringBuffer sb = new StringBuffer();
		int n = 0 ;
		for (Object segment : pathSegments) {
			n++ ;
			if (n > 1) { 
				sb.append(URI_SEPARATOR);
			}
			//--- encode 1 segment
			sb.append( encodeSegment(segment, characterEncoding) ) ;
		}
		return sb.toString();
	}
	
	/**
	 * Encodes the given segment object (returns "" if the given segment is null)
	 * @param segment
	 * @param characterEncoding
	 * @return
	 */
	private String encodeSegment(Object segment, String characterEncoding) {
		String encodedSegment = "" ;
		if ( segment != null ) {
			try {
				encodedSegment = UriUtils.encodePathSegment(segment.toString(), characterEncoding);
			} catch (UnsupportedEncodingException uee) {
				throw new RuntimeException("encodePathSegment error (UnsupportedEncodingException)", uee);
			}
		}
		return encodedSegment ;
	}

	/**
	 * Concatenates the given objects in a string
	 * @param objects
	 * @return
	 */
	protected String toString(Object ... objects) {
		String s = "" ;
		int i = 0 ;
		for ( Object o : objects ) {
			if ( i > 0 ) {
				s = s + "|" ;
			}
			s = s + o ;
			i++ ;
		}
		return s ;
	}

}