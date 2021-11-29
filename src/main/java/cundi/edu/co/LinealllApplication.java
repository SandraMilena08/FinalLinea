package cundi.edu.co;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import cundi.edu.co.swagger.SwaggerConfig;

@SpringBootApplication
@Import(SwaggerConfig.class)
public class LinealllApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinealllApplication.class, args);
	}
	
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
 
           registry.addResourceHandler("swagger-ui.html")
                    .addResourceLocations("classpath:/META-INF/resources/");
 
    }

}
