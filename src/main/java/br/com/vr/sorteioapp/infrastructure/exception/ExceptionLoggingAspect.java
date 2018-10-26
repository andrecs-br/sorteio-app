package br.com.vr.sorteioapp.infrastructure.exception;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import br.com.vr.sorteioapp.infrastructure.logger.Logger;
import br.com.vr.sorteioapp.infrastructure.logger.LoggerFactory;

@Aspect
public class ExceptionLoggingAspect {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionLoggingAspect.class);

	@Pointcut("execution(* br.com.vr.sorteioapp.*.*(..))")
	public void logginPointCut() {
		// pointcut
	}

	@Around("logginPointCut()")
	public Object logException(ProceedingJoinPoint pjp) throws Throwable {
		Object output;
		try {
			output = pjp.proceed();
		} catch (SorteioValidationException e) {
			logger.warn(e);
			throw e;
		}
		catch (Exception e) {
			logger.error(e);
			throw e;
		}
		return output;
	}

}
