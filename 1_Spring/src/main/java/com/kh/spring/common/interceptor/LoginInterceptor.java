package com.kh.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.spring.member.model.vo.Member;

public class LoginInterceptor implements HandlerInterceptor{
	//로그인과 회원가입의 log를 분리해주기 위해서 생성
	
	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		Member loginUser = (Member)request.getSession().getAttribute("loginUser");
	
		if(loginUser != null) {
			logger.info(loginUser.getId());
		}
	}
}
