package com.resume.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan({
        "com.resume.filter",
        "com.resume.model",
        "com.resume.service",
        "com.resume.listener"})
public class AppConfig {
}
