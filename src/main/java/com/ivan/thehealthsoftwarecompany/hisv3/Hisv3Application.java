package com.ivan.thehealthsoftwarecompany.hisv3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@SpringBootApplication
public class Hisv3Application implements WebMvcConfigurer {

    private final LocaleChangeInterceptor localeChangeInterceptor;

    public Hisv3Application(LocaleChangeInterceptor localeChangeInterceptor) {
        this.localeChangeInterceptor = localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logout").setViewName("logout");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
    }

    public static void main(String[] args) {
        SpringApplication.run(Hisv3Application.class, args);
    }


}
