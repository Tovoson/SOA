package com.app.emploi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173") // Remplacez par le domaine de votre client
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Selon les méthodes HTTP que vous souhaitez autoriser
                .allowedHeaders("*");
    }
}
