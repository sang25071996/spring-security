package sang.uaa.com.vn;

import javax.servlet.ServletContext;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
	
	@Override
	public void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		System.out.println(servletContext);
	}
}
