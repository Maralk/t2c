package com.proof.t2c.config;

import com.proof.t2c.domain.interactors.impl.UseCaseInteractorImpl;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan("com.proof.t2c.infrastructure.http.rest")
public class ApplicationConfig extends WebMvcConfigurerAdapter {

    @Bean
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UseCaseInteractorImpl useCaseInteractor() {
        return new UseCaseInteractorImpl();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
        resourceHandlerRegistry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        resourceHandlerRegistry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
