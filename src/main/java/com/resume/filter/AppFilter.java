package com.resume.filter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(0)
public class AppFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(AppFilter.class);

    @Value("${application.production}")
    private boolean production;
    @Value("${application.error-path}")
    private String errorPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestUrl = request.getRequestURI();
        request.setAttribute("REQUEST_URL", requestUrl);
        try {
            filterChain.doFilter(request, response);
        } catch (Throwable th) {
            LOGGER.error("Process request failed: " + requestUrl, th);
            handleException(th, requestUrl, response);
        }

    }

    private void handleException(Throwable th, String requestUrl, HttpServletResponse response) throws ServletException, IOException {
        if(production) {
            if (errorPath.equals(requestUrl)) {
                throw new ServletException(th);
            } else {
                response.sendRedirect(errorPath+ "?url="+requestUrl);
            }
        } else {
            throw new ServletException(th);
        }
    }

    @Override
    public void destroy() {

    }
}
