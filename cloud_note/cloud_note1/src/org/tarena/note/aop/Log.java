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
 * ���������
 * @author Administrator
 *
 */
@Component
@Aspect
public class Log  {
	
//	@Pointcut("execution(* cn.itcast.spring0909.aop.annotation.PersonDaoImpl.*(..))")
//	private void aa(){}//����ǩ��  ����ֵ������void ���������η������private
	
	
	public static final Logger logger = Logger.getLogger(Log.class);

	/**
	 * ǰ��֪ͨ
	 */
	@Before("execution(* org.tarena.note.controller.*.*(..))")
	public void beforeExcute(JoinPoint joinPoint){
		Object[] args = joinPoint.getArgs();//��ȡ�����Ĳ���
		String methodName = joinPoint.getSignature().getName();
		//System.out.println("���뷽������"+methodName);
		logger.info("���뷽������"+methodName);
		//System.out.println("���ݲ���ֵΪ:");
		logger.info(args.length);
		for(int i = 0;i<args.length;i++){
			logger.info("��"+(i+1)+"�������ǣ�"+args[i]);
		}
		
		logger.info("begin transaction");
	}
	
	/**
	 * �쳣֪ͨ
	 */
	@AfterThrowing(pointcut="execution(* org.tarena.note.controller.*.*(..))",
	               throwing="ex")
	public void exceptionMethod(Throwable ex){
		//System.err.println("���쳣�ˣ�"+ex.getCause())
		
		logger.error("���쳣�ˣ�"+ex.getCause());
	} 
	
	/**
	 * ����֪ͨ
	 */
	@After("execution(* org.tarena.note.controller.*.*(..))") 
	public void finallyMethod(){
		logger.info("����֪ͨ");
		
		//org.springframework.web.servlet.DispatcherServlet
	}
	
	/**
	 * ����֪ͨ
	 */
	@AfterReturning(pointcut="execution(* org.tarena.note.controller.*.*(..))",
			        returning="val")
	public void afterExcute(Object val){
		System.out.println("���ؽ��Ϊ��"+((NoteResult)val).toString());
		System.out.println("----------------------------");
	}
	
	/**
	 * ����֪ͨ
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Around("execution(* org.tarena.note.controller.*.*(..))")
	public Object aroundExcute(ProceedingJoinPoint joinPoint) throws Throwable{
		System.out.println("-----------------------------");
		System.out.println("����֪ͨǰ");
		
		Object[] args = joinPoint.getArgs();
		NoteResult result = null;
		if (args.length>0){
			result = (NoteResult)joinPoint.proceed(args);
		}else{
			result = (NoteResult)joinPoint.proceed();
		}
		System.out.println("����֪ͨ��");
		return result;
	}

}
