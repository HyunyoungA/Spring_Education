package com.kh.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect2 {//aspect-context.xml에 추가할 내용을 @Component: <bean>, @Aspect으로 추가하면 간단하게 끝낼 수 있다.
	
	private Logger logger = LoggerFactory.getLogger(LoggerAspect2.class);
	//프로그래밍적 aop
//	@Pointcut("execution(* com.kh.spring..*(..))") 밑에줄 코드와 합칠 수 있다.
//	public void pcForAll() {}//Pointcut
		
//	public void loggerAdvice(JoinPoint joinPoint) { //Before용
//	@Around("pcForAll")//어떤 Pointcut을 참조하는지 적어준다
	@Around("execution(* com.kh.spring..*(..))")//Pointcut + around
	public  Object loggerAdvice(ProceedingJoinPoint joinPoint) throws Throwable { //Around용
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
		
//		Object obj = null;
//		try {
//			obj = joinPoint.proceed();
//		} catch (Throwable e) {//printStackTrace(); 에러를 트라이캐치해서 잡았기때문에 (콘솔에는 나온다)-->에러를 잡아줘서 홈페이지에 흰화면으로 뜨는거다(공용화면으로 안넘어감)
//			e.printStackTrace();
//		}
		
		Object obj = joinPoint.proceed();//Throws해줘서 트라이캐치로 잡지않고 공용 에러페이지로 넘겨줘야한다.
		
		logger.debug("[After]" + componentName + type + "." + methodName + "()");
	
		return obj;
	}
	
	
	
	
	
}
