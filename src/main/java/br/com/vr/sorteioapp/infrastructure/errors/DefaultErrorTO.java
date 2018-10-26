package br.com.vr.sorteioapp.infrastructure.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.vr.sorteioapp.dto.response.OutputMessage;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class DefaultErrorTO implements Serializable {

	private static final long serialVersionUID = 6362350706186244386L;

	private final OutputMessage response;
	private transient ValidationErrorTO validacoes;

	public DefaultErrorTO(ErroCatalogado erroCatalogado) {
		this.response = new OutputMessage(erroCatalogado);
	}

	public DefaultErrorTO(ErroCatalogado erroCatalogado, ValidationErrorTO validacoes) {
		this(erroCatalogado);
		this.validacoes = validacoes;
	}
	
	public DefaultErrorTO(ErroCatalogado erroCatalogado, String message) {
		this(erroCatalogado);
		this.response.setMessage(message);
	}

	public OutputMessage getResponse() {
		return response;
	}

	public ValidationErrorTO getValidacoes() {
		return validacoes;
	}
}
