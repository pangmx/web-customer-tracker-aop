package com.mx.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	
	//create logger
	private Logger logger = Logger.getLogger(getClass().getName());

	//controller pointcut declaration
	@Pointcut("execution(* com.mx.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	//dao package pointcut declaration
	@Pointcut("execution(* com.mx.springdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	//service package pointcut declaration
	@Pointcut("execution(* com.mx.springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	//combine pointcuts
	@Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
	private void forWebflow() {}
	
	//Before advices
	@Before("forWebflow()")
	public void before(JoinPoint jp) {
		
		//display the method that we are calling
		String methodSig = jp.getSignature().toShortString();
		logger.info("----------- In Before Advice, calling method: " + methodSig);
		
		//logging the arguments to method
		Object[] methodArgs = jp.getArgs();
		
		//printing the arguments
		for(Object arg: methodArgs) {
			logger.info(">>>>>>>> Method argument: " + arg.toString());
		}
	}
	
	//AfterReturning advices
	@AfterReturning(pointcut="forWebflow()", 
			returning = "result")
	public void after(JoinPoint jp, Object result) {
		
		//display method that we are running
		String methodSig = jp.getSignature().getName();
		logger.info("----------- In AfterReturning advice, called method: "+methodSig);
		
		//print the method return results
		logger.info(">>>>>>>> Method results: "+result);
	}
}
