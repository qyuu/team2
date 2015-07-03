package javaee.team2.util;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class Tracer implements Serializable{
	@Inject
	transient Logger logger;
	@Inject
	LoggingUtil u;
	
	@AroundConstruct
	public void constructorLog(InvocationContext ic) throws Exception{
		logger.log(Level.FINE, "Constructor start : {0}[PARAM]{1}", new Object[]{u.ConstructorName(ic), u.paramList(ic)});
		try{
			ic.proceed();
		}finally{
			logger.log(Level.FINE, "Constructor finish : {0}", u.ConstructorName(ic));
		}
	}
	
	@AroundInvoke
	public Object methodLog(InvocationContext ic) throws Exception{
		logger.log(Level.FINE, "Method start : {0}#{1}[PARAM]{2}", new Object[]{u.className(ic), u.methodName(ic), u.paramList(ic)});
		Object result = null;
		try{
			result = ic.proceed();
			return result;
		}finally{
			logger.log(Level.FINE, "Method finish : {0}#{1}[RESULT]{2}", new Object[]{u.className(ic), u.methodName(ic), result});
		}
	}
}
