package com.kh.spring.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jTest {
	
	private Logger logger = LoggerFactory.getLogger(Log4jTest.class);
	//사용할 로그가 작성되어있는 클래스를 넣어주는 것이다

	public static void main(String[] args) {
		new Log4jTest().test();

	}
	public void test() {
		logger.error("error");
		logger.warn("warn");
		logger.info("info");
		//<logger name="com.kh.spring">
		//		<level value="info" />
		//	</logger> log4j.xml에 info로 설정해 놔서 그렇다
		logger.debug("debug");
		logger.trace("trace");
	}
}
