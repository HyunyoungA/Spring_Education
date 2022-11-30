package com.kh.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//public class TestInterceptor extends HandlerInterceptorAdapter{
public class TestInterceptor implements HandlerInterceptor{
	
	private Logger logger = LoggerFactory.getLogger(TestInterceptor.class);
	
	//오버라이딩의 조건은 절대로 바꾸면 안된다 
	//preHandle의 반환은 무조건 true
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("============ START ============");
		logger.debug(request.getRequestURI());
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.debug("----------------view-------------------");
	}
	//기존에 있을걸 실행시키지때문에 super는 지워줘도 상관없다.
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.debug("============ END ============\n");
	}
}
