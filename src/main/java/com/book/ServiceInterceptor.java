package com.book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ServiceInterceptor implements HandlerInterceptor {
		Logger logger = LoggerFactory.getLogger(LibraryApplication.class);
		
	   @Override
	   public boolean preHandle
	      (HttpServletRequest req, HttpServletResponse response, Object handler) 
	      throws Exception {	      
	      //System.out.println("Pre Handle method is Called");
	      logger.info("request method:"+req.getMethod()+", getQueryString:"+req.getQueryString()+", getRequestURI:"+req.getRequestURI()
	      +", getRequestURL:"+req.getRequestURL()+", pathInfo:"+req.getPathInfo()+", headerName:"+req.getHeaderNames());
	      return true;
	   }
	   @Override
	   public void postHandle(HttpServletRequest request, HttpServletResponse response, 
	      Object handler, ModelAndView modelAndView) throws Exception {
	      
	      logger.info("Post Handle method is Called");
	   }
	   @Override
	   public void afterCompletion
	      (HttpServletRequest request, HttpServletResponse response, Object 
	      handler, Exception exception) throws Exception {
	      
		   logger.info("Request and Response is completed");
	   }
}
