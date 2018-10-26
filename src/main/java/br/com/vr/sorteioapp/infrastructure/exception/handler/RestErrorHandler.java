package br.com.vr.sorteioapp.infrastructure.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import br.com.vr.sorteioapp.infrastructure.errors.DefaultErrorTO;
import br.com.vr.sorteioapp.infrastructure.errors.ValidationErrorTO;
import br.com.vr.sorteioapp.infrastructure.exception.ErroCatalogadoSorteio;
import br.com.vr.sorteioapp.infrastructure.exception.SorteioApplicationException;
import br.com.vr.sorteioapp.infrastructure.exception.SorteioParamValidationException;
import br.com.vr.sorteioapp.infrastructure.exception.SorteioValidationException;
import br.com.vr.sorteioapp.infrastructure.logger.Logger;
import br.com.vr.sorteioapp.infrastructure.logger.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@ControllerAdvice
public class RestErrorHandler {

	private final Logger logger = LoggerFactory.getLogger(RestErrorHandler.class);

	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity<DefaultErrorTO> handleException(HttpServletRequest request, Exception ex) {
		logger.error("SorteioException Occured:: URL=" + request.getRequestURL(), ex);
		return new ResponseEntity<>(new DefaultErrorTO(ErroCatalogadoSorteio.ERRO_INTERNO),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(SorteioApplicationException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity<DefaultErrorTO> handlePrazoPedidoException(SorteioApplicationException ex) {
		return new ResponseEntity<>(new DefaultErrorTO(ex.getErroCatalogado(),  ex.getMessage()),
										HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(SorteioValidationException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ResponseEntity<DefaultErrorTO> prazoPedidoValidationException(SorteioValidationException ex) {
		return new ResponseEntity<>(new DefaultErrorTO(
				ErroCatalogadoSorteio.ERRO_PARAMETROS_INVALIDOS), HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler({SorteioParamValidationException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<DefaultErrorTO> processValidationParamError(SorteioParamValidationException ex) {
		return new ResponseEntity<>(new DefaultErrorTO(ex.getErroCatalogado()),	HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({HttpMessageNotReadableException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<DefaultErrorTO> parseJsonDeserializerError(HttpMessageNotReadableException ex) {

		return new ResponseEntity<>(new DefaultErrorTO
				(ErroCatalogadoSorteio.ERRO_PARAMETROS_INVALIDOS, ex.getCause().getMessage()),
				HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<DefaultErrorTO> processValidationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		return new ResponseEntity<>(
				new DefaultErrorTO(ErroCatalogadoSorteio.ERRO_PARAMETROS_INVALIDOS, processFieldErrors(fieldErrors)),
				HttpStatus.BAD_REQUEST
				);
	}
	
	@ExceptionHandler({MethodArgumentTypeMismatchException.class})
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ResponseBody
	public ResponseEntity<DefaultErrorTO> processValidationError(MethodArgumentTypeMismatchException ex) {
		ValidationErrorTO dto = new ValidationErrorTO();
		dto.addFieldError(
				ex.getName(), 
				Optional.of(ex.getMostSpecificCause())
				.orElse(new SorteioApplicationException(ErroCatalogadoSorteio.ERRO_PARAMETROS_INVALIDOS))
				.getMessage());
		return new ResponseEntity<>(new DefaultErrorTO(ErroCatalogadoSorteio.ERRO_PARAMETROS_INVALIDOS, dto),HttpStatus.UNPROCESSABLE_ENTITY);
	}
	

	private ValidationErrorTO processFieldErrors(List<FieldError> fieldErrors) {
		ValidationErrorTO dto = new ValidationErrorTO();

		for (FieldError fieldError : fieldErrors) {
			dto.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
		}

		return dto;
	}

}
