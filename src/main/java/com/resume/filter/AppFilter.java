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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String requestUrl = httpServletRequest.getRequestURI();
        httpServletRequest.setAttribute("REQUEST_URL", requestUrl);

        try {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (Throwable th) {
            LOGGER.error("Process request failed: " + requestUrl, th);
            handleException(th, requestUrl, httpServletResponse);
        }

    }

    private void handleException(Throwable th, String requestUrl, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        if(production) {
            if ("/error".equals(requestUrl)) {
                throw new ServletException(th);
            } else {
                httpServletResponse.sendRedirect("/error?url="+requestUrl);
            }
        } else {
            throw new ServletException(th);
        }
    }

    @Override
    public void destroy() {

    }
}
