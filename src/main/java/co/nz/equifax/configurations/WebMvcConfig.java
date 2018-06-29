package co.nz.equifax.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

	
}
