package wyh.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.RateLimiter;

import wyh.annotation.ExtRateLimit;

@Component
@Aspect
public class MyAspect {
	private Map<String, RateLimiter> rateMap = new ConcurrentHashMap<String, RateLimiter>();

	@Pointcut("execution(* wyh.controller..*.*(..))")
	public void execute(){
		
	}
	@Around("execute()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		
		MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
		Method method = signature.getMethod();
		ExtRateLimit annotation = method.getAnnotation(ExtRateLimit.class);
		// 获取注解的值
		long timeOut = annotation.timeOut();
		int timePerSecond = annotation.timePerSecond();
		if(annotation!=null){
			Object[] args = proceedingJoinPoint.getArgs();
			boolean tryAcquire = false;
			for (int i = 0; i < args.length; i++) {
				if(args[i] instanceof HttpServletRequest){
					HttpServletRequest request = (HttpServletRequest)args[i];
					String url = request.getRequestURI();
					boolean containsKey = rateMap.containsKey(url);
					if(containsKey){
						tryAcquire = rateMap.get(url).tryAcquire(timeOut, TimeUnit.MILLISECONDS);
					}else{
						RateLimiter create = RateLimiter.create(timePerSecond);
						rateMap.put(url, create);
						tryAcquire =create.tryAcquire(timeOut, TimeUnit.MILLISECONDS);
					}
					
				}
				
			}
			if(!tryAcquire) return "失败";
			
			
			
		}
		return proceedingJoinPoint.proceed();
		
		
		
	}
}
