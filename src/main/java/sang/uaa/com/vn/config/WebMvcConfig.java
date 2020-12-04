package sang.uaa.com.vn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import sang.uaa.com.vn.config.interceptor.RequestInterceptor;

/**
 * <p>
 * WebMvcConfig Interceptors
 * </p>
 * -------------------
 * 
 * @author macbook Nov 12, 2020
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestIntercepter());
	}
	
	@Bean
	public RequestInterceptor requestIntercepter() {
		return new RequestInterceptor();
	}
}
