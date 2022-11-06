package com.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LoggingAdvice implements MethodInterceptor{
	public Object invoke(MethodInvocation invocation) throws Throwable{
		System.out.println("before system call : "+invocation.getMethod());
		
		Object object = invocation.proceed();
		
		System.out.println("after system call : "+invocation.getMethod());
		
		return object;
	}

}
