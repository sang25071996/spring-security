package sang.uaa.com.vn.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sang.uaa.com.vn.common.ResponJson;
import sang.uaa.com.vn.common.SysError;
import sang.uaa.com.vn.utils.exception.AuthorizationException;
import sang.uaa.com.vn.utils.exception.BadRequestException;
import sang.uaa.com.vn.utils.exception.NotFoundException;

/**
 * <p>Handler Global Exception</p>
 * @author macbook
 * Nov 13, 2020
 */
@ControllerAdvice(basePackages = { "sang.uaa.com.vn" })
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler implements HandlerExceptionResolver {
	
	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	private static final String ERROR_MGS = "[sang-uaa]: ";
	
	@ResponseBody
	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ResponJson> exception(Exception ex) {
		LOG.error(ERROR_MGS, ex.getMessage(), ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponJson(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
	}
	
	@ResponseBody
	@ExceptionHandler(value = BadRequestException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<ResponJson> badRequestException(BadRequestException badRequestException) {
		String message = badRequestException.getMessage();
		LOG.error(ERROR_MGS, badRequestException.getMessage(), badRequestException);
		SysError ex = badRequestException.getSysError();
		if (ObjectUtils.isEmpty(ex)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponJson(message));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponJson(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex));
		}
	}
	
	@ResponseBody
	@ExceptionHandler(value = AuthorizationException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public ResponseEntity<ResponJson> authorizationException(AuthorizationException authorizationException) {
		String message = authorizationException.getMessage();
		LOG.error(ERROR_MGS, authorizationException.getMessage(), authorizationException);
		SysError ex = authorizationException.getSysError();
		if (ObjectUtils.isEmpty(ex)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponJson(message));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ResponJson(HttpStatus.UNAUTHORIZED.getReasonPhrase(), ex));
		}
		
	}
	
	@ResponseBody
	@ExceptionHandler(value = NotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<ResponJson> notFoundException(NotFoundException notFoundException) {
		String message = notFoundException.getMessage();
		LOG.error(ERROR_MGS, notFoundException.getMessage(), notFoundException);
		SysError ex = notFoundException.getSysError();
		if (ObjectUtils.isEmpty(ex)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponJson(message));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponJson(HttpStatus.NOT_FOUND.getReasonPhrase(), ex));
		}
		
	}
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		try {
			if (ex instanceof IllegalArgumentException) {
				return handleIllegalArgument((IllegalArgumentException) ex, request, response, handler);
			}
		} catch (Exception handlerException) {
			logger.warn("Handling of [{}] resulted in Exception", handlerException);
		}
		return null;
	}
	
	private ModelAndView handleIllegalArgument(IllegalArgumentException ex, final HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		final String accept = request.getHeader(HttpHeaders.ACCEPT);
		
		response.sendError(HttpServletResponse.SC_CONFLICT);
		response.setHeader("ContentType", accept);
		
		final ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("error", prepareErrorResponse(accept));
		return modelAndView;
	}
	
	/**
	 * Prepares error object based on the provided accept type.
	 * 
	 * @param accept The Accept header present in the request.
	 * @return The response to return
	 * @throws JsonProcessingException
	 */
	private String prepareErrorResponse(String accept) throws JsonProcessingException {
		final Map<String, String> error = new HashMap<>();
		error.put("Error", "Application specific error message");
		
		String response = null;
		if (MediaType.APPLICATION_JSON_VALUE.equals(accept)) {
			response = new ObjectMapper().writeValueAsString(error);
		}
		return response;
	}
	
}
