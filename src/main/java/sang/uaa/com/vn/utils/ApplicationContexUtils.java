package sang.uaa.com.vn.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContexUtils implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		setContext(applicationContext);
	}
	
	public static ApplicationContext getContext() {
		return applicationContext;
	}
	
	public static void setContext(ApplicationContext applicationContext) {
		ApplicationContexUtils.applicationContext = applicationContext;
	}
}
