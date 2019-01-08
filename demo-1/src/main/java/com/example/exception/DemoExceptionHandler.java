package com.example.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class DemoExceptionHandler {
	public static final String EXAMPLE_ERROR_VIEW="error";
	@ExceptionHandler(value = Exception.class)
	public Object errorHandler(HttpServletRequest request,HttpServletResponse response,Exception e) {
		e.printStackTrace();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception",e);
		mav.addObject("url", request.getRequestURL());
		mav.setViewName(EXAMPLE_ERROR_VIEW);
		return mav;
	}
}

