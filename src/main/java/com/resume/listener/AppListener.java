package com.resume.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {
    private static Logger LOGGER = Logger.getLogger(AppListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("\n<--------------------------------------------------------------------------->\n" +
                "<------------------------------APPLICATION STARTED-------------------------->\n" +
                "<--------------------------------------------------------------------------->");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.info("\n<--------------------------------------------------------------------------->\n" +
                "<------------------------------APPLICATION DESTROYED------------------------>\n" +
                "<--------------------------------------------------------------------------->");
    }
}
