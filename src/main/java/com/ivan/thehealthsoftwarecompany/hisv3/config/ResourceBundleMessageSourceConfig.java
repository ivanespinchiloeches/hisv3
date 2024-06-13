package com.ivan.thehealthsoftwarecompany.hisv3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

@Configuration
public class ResourceBundleMessageSourceConfig {
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("lang/messages");
        //source.setBasename("messages");
        source.setDefaultEncoding("UTF-8");

        System.out.println(source.getMessage("hello", null, Locale.ENGLISH));
        return source;
    }
}
