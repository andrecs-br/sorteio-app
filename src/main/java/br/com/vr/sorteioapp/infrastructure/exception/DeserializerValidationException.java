package br.com.vr.sorteioapp.infrastructure.exception;


import java.io.IOException;

import br.com.vr.sorteioapp.infrastructure.errors.ErroCatalogado;

public class DeserializerValidationException extends IOException {

	private static final long serialVersionUID = 1L;

	public DeserializerValidationException(ErroCatalogado erroCatalogado, Exception e) {

		super(erroCatalogado.getDescricao(), e);
	}

	public DeserializerValidationException(ErroCatalogado erroCatalogado, String message) {
		super(erroCatalogado.getDescricao() + message);
	}
}
