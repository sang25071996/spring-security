package sang.uaa.com.vn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import sang.uaa.com.vn.common.MessageService;
import sang.uaa.com.vn.service.impl.TeaSeviceImpl;

//@SpringBootApplication(exclude = { ContextStackAutoConfiguration.class })
@SpringBootApplication
@ComponentScan(basePackages = "sang.uaa.com.vn.*")
@EnableJpaRepositories(basePackages = "sang.uaa.com.vn.repository")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableCaching
public class DemoApplication implements ApplicationListener<ContextRefreshedEvent> {
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		TeaSeviceImpl teaSeviceImpl = new TeaSeviceImpl();
		System.out.println(teaSeviceImpl.makeOlong());
		System.out.println("---------sangTest--------");
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		MessageService messageService = MessageService.getInstance();
		messageService.init("message/ApplicationMessage");
	}
}
