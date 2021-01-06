package sang.uaa.com.vn.common.bean;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextHolder implements ApplicationContextAware {
	
	private static Map<String, ApplicationContext> contextMap = new HashMap<>();
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		contextMap.put(ApplicationContext.class.getName(), applicationContext);
		
	}
	/**
	 * 
	 * <p>ApplicationContext</p>
	 * <p>Dec 26, 2020</p>
	 * -------------------
	 * @author macbook
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return contextMap.get(String.valueOf(ApplicationContext.class.getClass()));
	}
}
