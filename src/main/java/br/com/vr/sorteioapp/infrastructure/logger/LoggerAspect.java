package br.com.vr.sorteioapp.infrastructure.logger;

import br.com.vr.lcj.log.debugger.ToStringDebugger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LoggerAspect {
	
	
	private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);
	final ToStringDebugger debugger = new ToStringDebugger(logger::debug);

	@Around("within(br.com.vr.prazopedido.*)")
	public Object debug(ProceedingJoinPoint pjp) throws Throwable {
		MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
		String info = String.format("%s.%s", pjp.getTarget().getClass().getSimpleName(), method.getName());
		logger.debug("Init {}", info);
		debugger.print(pjp.getArgs(), info);
        Object output = pjp.proceed();
		debugger.print(output, info);
		logger.debug("End {}.", info);
		return output;
	}

}
