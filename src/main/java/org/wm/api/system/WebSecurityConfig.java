package org.wm.api.system;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class WebSecurityConfig implements WebMvcConfigurer {
    
    private final CORSHost corsHost;
    
    @Value("${application.storage.mapping}")
    private String storage;
        
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods(corsHost.getMethods().toArray(String[]::new))
                .allowedHeaders("*")
                .allowedOrigins(corsHost.getHost());
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(("/uploads/**"))
                .addResourceLocations(storage);
    }
}
