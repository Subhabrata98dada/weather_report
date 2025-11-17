package com.qsp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class WeatherReportAspect {
	
	@Before("execution(* com.qsp.serviceimplement.WeatherServiceImplement.*(..))")
	public void beforeApiCallLog(JoinPoint joinpoint) {
		log.info(joinpoint.getTarget().getClass().getName()+"."+
	    joinpoint.getSignature().getName()+ " method called");
	}
	
	@AfterReturning("execution(* com.qsp.serviceimplement.WeatherServiceImplement.*(..))")
	public void afterApiCallLog(JoinPoint joinpoint) {
		log.info(joinpoint.getTarget().getClass().getName()+"."+
	    joinpoint.getSignature().getName()+ " method executed");
	}
	
	@AfterThrowing(pointcut = "execution(* com.qsp.serviceimplement.WeatherServiceImplement.*(..))",
			throwing = "ex")
	public void afterexception(JoinPoint joinpoint,Throwable ex) {
		log.error(joinpoint.getTarget().getClass().getName()+"."+
			    joinpoint.getSignature().getName()+ ex.getMessage());
	}
	
	@Around("execution(* com.qsp.serviceimplement.WeatherServiceImplement.*(..))")
	public Object executiontime(ProceedingJoinPoint joinpoint) throws Throwable {
		long previous=System.currentTimeMillis();
		Object result=joinpoint.proceed();
		long current=System.currentTimeMillis();
		log.info(joinpoint.getTarget().getClass().getName()+"."
				+joinpoint.getSignature().getName()+" needs "+(current-previous)+" mili second");
		return result;
	}
}
