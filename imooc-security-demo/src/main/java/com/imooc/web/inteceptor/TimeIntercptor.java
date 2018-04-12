package com.imooc.web.inteceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
//@Component
public class TimeIntercptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
			throws Exception {
		System.out.println("TimeIntercptor.afterCompletion()");
		Long start = (Long)request.getAttribute("startTime");
		System.out.println("time interceptor 耗时："+ (new Date().getTime()-start));
		System.out.println("exception is "+e);
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		System.out.println("TimeIntercptor.postHandle()");
		Long start = (Long)request.getAttribute("startTime");
		System.out.println("time interceptor 耗时："+ (new Date().getTime()-start));
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("TimeIntercptor.preHandle()");
		System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
		System.out.println(((HandlerMethod)handler).getMethod().getName());
		request.setAttribute("startTime", new Date().getTime());
		
		return true;
	}

}
