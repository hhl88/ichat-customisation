package com.novomind.ecom.ichat.customisation.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class CorsFilter implements Filter {
    Logger log = LoggerFactory.getLogger(CorsFilter.class);
    static final String ORIGIN = "origin";
    static final String ALLOWED_HEADERS = "access-control-request-headers";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init CorsFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpServletResponse response = (HttpServletResponse) servletResponse;

//        if (request.getHeader(ORIGIN) != null) {
//            String origin = request.getHeader(ORIGIN);
//            response.setHeader("Access-Control-Allow-Origin", origin);
//        }

        response.setHeader("Access-Control-Allow-Origin", "https://frontend.customisation.test:4321");

        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");

        response.setHeader("Access-Control-Allow-Headers", request.getHeader(ALLOWED_HEADERS));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "180");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        log.info("destroy CorsFilter");
    }

}
