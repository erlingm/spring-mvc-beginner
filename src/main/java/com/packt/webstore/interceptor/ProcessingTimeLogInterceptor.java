package com.packt.webstore.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Erling Molde on 03.11.2016.
 */
public class ProcessingTimeLogInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = Logger.getLogger(ProcessingTimeLogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String queryString = request.getQueryString() == null ? "" : "?" + request.getQueryString();
        StringBuffer path = request.getRequestURL().append(queryString);
        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        LOGGER.info(String.format("%s milliseconds taken to process the request %s", (endTime - startTime), path));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exceptionIfAny) throws Exception {
        // NO operation
    }
}
