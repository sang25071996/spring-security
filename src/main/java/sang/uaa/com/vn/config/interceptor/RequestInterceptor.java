package sang.uaa.com.vn.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * <p>
 * RequestIntercepter
 * </p>
 * <p>
 * Handler Request add the request header before sending the request to the
 * controller and add the response header before sending the response to the
 * client.
 * </p>
 * 
 * @author macbook Nov 12, 2020
 */

public class RequestInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger LOG = LoggerFactory.getLogger(RequestInterceptor.class);
	private Long startTime;
	private Long endTime;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		this.startTime = System.currentTimeMillis();
		LOG.info("Request URL: {}", request.getRequestURL());
		LOG.info("Start time: {}", this.startTime);
		request.setAttribute("startTime", this.startTime);
		String user;
		if (SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null) {
			user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			LOG.info("User {} has been login", user);
		}
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		this.startTime = (Long) request.getAttribute("startTime");
		this.endTime = System.currentTimeMillis();
		LOG.info("Request URL: {}", request.getRequestURL());
		LOG.info("End Time: {}", endTime);
		LOG.info("Time Taken: {} milliseconds", (endTime - startTime));
	}
}
