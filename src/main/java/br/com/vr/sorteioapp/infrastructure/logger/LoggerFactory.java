package br.com.vr.sorteioapp.infrastructure.logger;


public class LoggerFactory {

	private LoggerFactory() {
		throw new IllegalStateException("Utility class");
	}

	public static Logger getLogger(Class<?> clazz){
		org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(clazz);
        return new Logger(logger);
	}

	public static Logger getLogger(String loggerName){
		org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(loggerName);
        return new Logger(logger);
	}

}
