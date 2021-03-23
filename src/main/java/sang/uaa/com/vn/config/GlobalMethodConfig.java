package sang.uaa.com.vn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import sang.uaa.com.vn.config.security.CustomPermissionEvaluator;

@Configuration
@EnableGlobalMethodSecurity(
		prePostEnabled = true,
		securedEnabled =  true,
		jsr250Enabled =  true
	)
public class GlobalMethodConfig extends GlobalMethodSecurityConfiguration {
	
	@Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler = 
          new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(new CustomPermissionEvaluator());
        return expressionHandler;
    }
}
