package sang.uaa.com.vn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class MethodValidationConfig {

	/**
	 * <p>add config custom annotation validate</p>
	 * <p>Local Validator Factory Bean</p>
	 * <p>Jan 10, 2021</p>
	 * -------------------
	 * @author macbook
	 * @return LocalValidatorFactoryBean
	 */
	@Bean
	public LocalValidatorFactoryBean setValidator() {
	    return new LocalValidatorFactoryBean();
	}
}
