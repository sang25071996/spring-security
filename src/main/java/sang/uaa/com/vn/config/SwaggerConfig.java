package sang.uaa.com.vn.config;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sang.uaa.com.vn.constant.Constants;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${swagger-url")
	private  String host;
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)
        .host(host)
        .globalOperationParameters(globalParameterList())
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
	private List<Parameter> globalParameterList() {
		Parameter parameter = new ParameterBuilder()
				.name(Constants.AUTHORIZATION_HEADER)
				.modelRef(new ModelRef("String"))
				.parameterType("header")
				.description("Token")
				.build();
		return Collections.singletonList(parameter);
	}
}
