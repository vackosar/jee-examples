package com.vackosar.jms2dbsaver.control;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class DefaultInterceptor {
	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {
		System.out.println("INTERCEPTED " + ctx.getMethod().getName());
		Object result = ctx.proceed();
		return result;
	}
}
