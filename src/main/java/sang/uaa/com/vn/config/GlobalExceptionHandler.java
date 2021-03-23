package sang.uaa.com.vn.config;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import sang.uaa.com.vn.common.dto.ResponJson;
import sang.uaa.com.vn.common.dto.SysError;
import sang.uaa.com.vn.exception.BadRequestException;
import sang.uaa.com.vn.exception.NotFoundException;

/**
 * <p>
 * Handler Global Exception
 * </p>
 * 
 * @author macbook Nov 13, 2020
 */
@ControllerAdvice(basePackages = { "sang.uaa.com.vn" })
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
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
	@ExceptionHandler(value = { BadCredentialsException.class })
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public ResponseEntity<ResponJson> authorizationException(AuthenticationException authorizationException) {
		String message = authorizationException.getMessage();
		LOG.error(ERROR_MGS, authorizationException.getMessage(), authorizationException);
	
		if (ObjectUtils.isEmpty(message)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponJson(message));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ResponJson("Username or Pasword incorrect"));
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
	
	@ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity accessDeniedException(AccessDeniedException e) throws AccessDeniedException {
		LOG.info(e.toString());
        throw e;
    }
	
}
