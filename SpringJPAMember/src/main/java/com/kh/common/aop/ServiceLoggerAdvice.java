package com.kh.common.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect // 위빙한다.
//요 전체가 Aspect == Advisor
public class ServiceLoggerAdvice {
	// 이게 joint CUT
	// @Before가 Weaving!!! 인데 앞에 넣는!
//	@After("execution(* com.kh.service.MemberDAOService*.*(..))")
	// 이건 advice // (JoinPoint jp)가 Point CUT의 대상!
	public void startLog(JoinPoint jp) {
		// 횡단 관심
		log.info("stopLog");
		log.info("stopLog : " + jp.getSignature());
		if (jp.getArgs() != null) {
			log.info("stopLog : " + Arrays.toString(jp.getArgs()));
		}
	}

	@Around("execution(* com.kh.service.MemberDAOService*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.currentTimeMillis();
		// insert(), select(), delete(), selectAll(), serviceImpl 등을 실행하는 것
		// 핵심 관심 실행 부분
		Object result = pjp.proceed();
		//핵심 관심 종료 후 시간
		long endTime = System.currentTimeMillis();
		log.info(pjp.getSignature().getName() + " : " + (endTime - startTime));
		return result;
	}
}