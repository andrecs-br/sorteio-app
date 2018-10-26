package br.com.vr.sorteioapp.infrastructure.logger;

import br.com.vr.sorteioapp.infrastructure.exception.ApplicationException;

public class Logger {

	private final org.slf4j.Logger loggerPrazoPedido;
	private static final org.slf4j.Logger stacktraceLogger = org.slf4j.LoggerFactory.getLogger("stacktrace");

	public Logger(org.slf4j.Logger logger) {
		super();
		this.loggerPrazoPedido = logger;
	}

	public void error(Throwable e) {
		loggerPrazoPedido.error("Exception: Message: {}. Ver stacktrace no arquivo stacktrace.log", e.getMessage());
		stacktraceLogger.trace("Exception", e);
	}

	public void error(String format, Throwable e, Object... arguments) {
		this.loggerPrazoPedido.error(format, arguments);
		stacktraceLogger.trace("Stacktrace Exception.", e);
	}

	public void warn(ApplicationException e) {
		String str = e.getErroCatalogado()!=null ? "ErroCatalogado {0}." : "WARN.";
		str += e.toString();
		loggerPrazoPedido.warn(str);
	}
	public void info(String format, Object... arguments) {
		loggerPrazoPedido.info(format, arguments);
	}

	public void warn(String message) {
		loggerPrazoPedido.warn(message);
	}

	public void warn(String format, Object... arguments) {
		loggerPrazoPedido.warn(format, arguments);
	}

	public void debug(String format, Object... arguments) {
		loggerPrazoPedido.debug(format, arguments);
	}
	
}
