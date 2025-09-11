package com.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Global CORS configuration applied to all /api/** endpoints
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Only for API routes
                .allowedOrigins(
                        "http://localhost:5173",                // Local frontend
                        "https://drone-frontend-one.vercel.app",
                        "https://drone-backend-dsg3.onrender.com" // ✅ Vercel deployed frontend
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    /**
     * Keep React routing support for non-API paths
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/{path:[^\\.]*}")
                .setViewName("forward:/index.html");
    }
}
