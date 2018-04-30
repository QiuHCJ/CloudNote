package org.tarena.note.aop;


import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.tarena.note.entity.NoteResult;

/**
 * 切面测试类
 * @author Administrator
 *
 */
@Component
@Aspect
public class Log  {
	
//	@Pointcut("execution(* cn.itcast.spring0909.aop.annotation.PersonDaoImpl.*(..))")
//	private void aa(){}//方法签名  返回值必须是void 方法的修饰符最好是private
	
	
	public static final Logger logger = Logger.getLogger(Log.class);

	/**
	 * 前置通知
	 */
	@Before("execution(* org.tarena.note.controller.*.*(..))")
	public void beforeExcute(JoinPoint joinPoint){
		Object[] args = joinPoint.getArgs();//获取方法的参数
		String methodName = joinPoint.getSignature().getName();
		//System.out.println("进入方法名："+methodName);
		logger.info("进入方法名："+methodName);
		//System.out.println("传递参数值为:");
		logger.info(args.length);
		for(int i = 0;i<args.length;i++){
			logger.info("第"+(i+1)+"个参数是："+args[i]);
		}
		
		logger.info("begin transaction");
	}
	
	/**
	 * 异常通知
	 */
	@AfterThrowing(pointcut="execution(* org.tarena.note.controller.*.*(..))",
	               throwing="ex")
	public void exceptionMethod(Throwable ex){
		//System.err.println("出异常了："+ex.getCause())
		
		logger.error("出异常了："+ex.getCause());
	} 
	
	/**
	 * 最终通知
	 */
	@After("execution(* org.tarena.note.controller.*.*(..))") 
	public void finallyMethod(){
		logger.info("最终通知");
		
		//org.springframework.web.servlet.DispatcherServlet
	}
	
	/**
	 * 后置通知
	 */
	@AfterReturning(pointcut="execution(* org.tarena.note.controller.*.*(..))",
			        returning="val")
	public void afterExcute(Object val){
		System.out.println("返回结果为："+((NoteResult)val).toString());
		System.out.println("----------------------------");
	}
	
	/**
	 * 环绕通知
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Around("execution(* org.tarena.note.controller.*.*(..))")
	public Object aroundExcute(ProceedingJoinPoint joinPoint) throws Throwable{
		System.out.println("-----------------------------");
		System.out.println("环绕通知前");
		
		Object[] args = joinPoint.getArgs();
		NoteResult result = null;
		if (args.length>0){
			result = (NoteResult)joinPoint.proceed(args);
		}else{
			result = (NoteResult)joinPoint.proceed();
		}
		System.out.println("环绕通知后");
		return result;
	}

}
