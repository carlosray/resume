package com.resume.configuration;

import com.resume.filter.AppFilter;
import com.resume.listener.AppListener;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ResumeWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext context = createWebApplicationContext(servletContext);
        registerDispatcherServlet(servletContext, context);
        registerFilters(servletContext, context);
        registerListeners(servletContext, context);
    }

    private void registerFilters(ServletContext servletContext, WebApplicationContext context) {
        servletContext.addFilter(AppFilter.class.getName(), context.getBean(AppFilter.class))
                .addMappingForUrlPatterns(null, true, "/*");
        servletContext.addFilter(CharacterEncodingFilter.class.getName(), new CharacterEncodingFilter("UTF-8", true))
                .addMappingForUrlPatterns(null, true, "/*");
        servletContext.addFilter("sitemesh", context.getBean(ConfigurableSiteMeshFilter.class))
                .addMappingForUrlPatterns(null, true, "/*");
    }

    private void registerListeners(ServletContext servletContext, WebApplicationContext context) {
        servletContext.addListener(context.getBean(AppListener.class));
    }

    private WebApplicationContext createWebApplicationContext(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.scan("com.resume.configuration");
        context.setServletContext(servletContext);
        context.refresh();
        return context;
    }

    private void registerDispatcherServlet(ServletContext servletContext, WebApplicationContext context) {
        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}
