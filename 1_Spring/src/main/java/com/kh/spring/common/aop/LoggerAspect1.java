package com.kh.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerAspect1 {
	//선언적 aop
	private Logger logger = LoggerFactory.getLogger(LoggerAspect1.class);
	
//	public void loggerAdvice(JoinPoint joinPoint) { //Before용
	public  Object loggerAdvice(ProceedingJoinPoint joinPoint) { //Around용
		Signature signature = joinPoint.getSignature();
		logger.debug("signature : " + signature);
		
		String type = signature.getDeclaringTypeName(); //메소드가 있는 클래스 풀네임
		String methodName = signature.getName();
		logger.debug("type : " + type);
		logger.debug("methodName : "+ methodName);
		
		String componentName = null;
		if(type.indexOf("Controller") > -1) {//-1은 존재하지않는거니까 어딘가에 존재하면
			componentName = "Controller  \t :";
		} else if(type.indexOf("Service") > -1) {
			componentName = "ServiceImpl  \t: ";
		} else if(type.indexOf("DAO") > -1) {
			componentName = "DAO  \t\t: ";
		}
		//타겟오브젝트 실행전에 비포실행 , 타겟오브젝트 실행 후 에프터실행 + joinPoint.proceed()추가
		logger.debug("[Before]" + componentName + type + "." + methodName + "()");
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		logger.debug("[After]" + componentName + type + "." + methodName + "()");
	
		return obj;
	}
	
	
	
	
	
}
