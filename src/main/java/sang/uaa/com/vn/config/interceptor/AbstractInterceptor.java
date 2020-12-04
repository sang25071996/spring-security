package sang.uaa.com.vn.config.interceptor;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 	AbstractInterceptor using spring AOP
 *  Advice that logs methods in the application's main packages.
 * </p>
 * ----------------------
 * @author macbook
 * Nov 13, 2020
 */
@Component
@Aspect
public class AbstractInterceptor {
	
	private static final Logger LOG = LoggerFactory.getLogger(AbstractInterceptor.class);
	
	/**
	 * 
	 * <p>In Controller Layer</p>
	 * Nov 13, 2020
	 *-------------------
	 * @author macbook
	 * @param point
	 * @throws Throwable
	 */
	@Around("within(sang.uaa.com.vn.controller..*)")
	public Object inControllerLayer(ProceedingJoinPoint point) throws Throwable {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Start Controller Method: {}.{}() with argument[s] = {}", point.getSignature().getDeclaringTypeName(),
					point.getSignature().getName(), Arrays.toString(point.getArgs()));
		}
		try {
			Object result = point.proceed();
			if (LOG.isDebugEnabled()) {
				LOG.debug("Exit Controller Method: {}.{}() with result = {}", point.getSignature().getDeclaringTypeName(),
						point.getSignature().getName(), result);
			}
			return result;
		} catch (IllegalArgumentException e) {
			LOG.error("Illegal argument: {} in {}.{}()", Arrays.toString(point.getArgs()),
					point.getSignature().getDeclaringTypeName(), point.getSignature().getName());
			throw e;
		}
	}
	
	/**
	 * 
	 * <p>In Service Layer</p>
	 * <p>Nov 30, 2020</p>
	 * -------------------
	 * @author macbook
	 * @param point
	 * @return Object
	 * @throws Throwable
	 */
	@Around("within(sang.uaa.com.vn.service.impl..*)")
	public Object inServiceLayer(ProceedingJoinPoint point) throws Throwable {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Start Service Method: {}.{}() with argument[s] = {}", point.getSignature().getDeclaringTypeName(),
					point.getSignature().getName(), Arrays.toString(point.getArgs()));
		}
		try {
			Object result = point.proceed();
			if (LOG.isDebugEnabled()) {
				LOG.debug("Exit Service Method: {}.{}() with result = {}", point.getSignature().getDeclaringTypeName(),
						point.getSignature().getName(), result);
			}
			return result;
		} catch (IllegalArgumentException e) {
			LOG.error("Illegal argument: {} in {}.{}()", Arrays.toString(point.getArgs()),
					point.getSignature().getDeclaringTypeName(), point.getSignature().getName());
			throw e;
		}
	}
}
