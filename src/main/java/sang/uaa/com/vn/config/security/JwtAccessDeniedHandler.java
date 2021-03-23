package sang.uaa.com.vn.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * 
 * <p>JwtAccessDeniedHandler handle request</p>
 * Nov 14, 2020
 *-------------------
 * @author macbook
 */
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());

        response.sendRedirect(request.getContextPath() + "/access-denied");
	}

}
