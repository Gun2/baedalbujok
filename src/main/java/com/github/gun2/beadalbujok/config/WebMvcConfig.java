package com.github.gun2.beadalbujok.config;

import com.github.gun2.beadalbujok.component.PreAuthComponent;
import com.github.gun2.beadalbujok.interceptor.AuthCheckInterceptor;
import com.github.gun2.beadalbujok.interceptor.PreAuthCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final PreAuthComponent preAuthComponent;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:images/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthCheckInterceptor());
        if(preAuthComponent.isActive()){
            registry.addInterceptor(new PreAuthCheckInterceptor(preAuthComponent))
                    .excludePathPatterns("/pre-auth/**");
        }
    }
}
