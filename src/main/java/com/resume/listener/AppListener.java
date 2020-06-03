package com.resume.listener;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Log4j
@Component
public class AppListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("\n<--------------------------------------------------------------------------->\n" +
                "<------------------------------APPLICATION STARTED-------------------------->\n" +
                "<--------------------------------------------------------------------------->");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("\n<--------------------------------------------------------------------------->\n" +
                "<------------------------------APPLICATION DESTROYED------------------------>\n" +
                "<--------------------------------------------------------------------------->");
    }
}
