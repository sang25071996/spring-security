package sang.uaa.com.vn.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerConfiguration {

	@Bean(name = "org.dozer.Mapper")
	public DozerBeanMapper dozerBean() {
		DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
		return dozerBeanMapper;
	}
}