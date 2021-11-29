package cundi.edu.co.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .useDefaultResponseMessages(false)
          .apiInfo(apiInfo())
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("cundi.edu.co.controller"))              
          .paths(PathSelectors.any())                          
          .build();    	
        
    }
	
    private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Servicios Universidad")
				.description("API Servicios de profesor y estudiante "
						+ "Realizado por: Sandra Moreno - Erika Moreno")
				.build();
	}

}

