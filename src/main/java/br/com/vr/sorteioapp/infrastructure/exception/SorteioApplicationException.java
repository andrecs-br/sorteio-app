package br.com.vr.sorteioapp.infrastructure.exception;

import br.com.vr.sorteioapp.infrastructure.errors.ErroCatalogado;

public class SorteioApplicationException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public SorteioApplicationException(ErroCatalogado erroCatalogado, Exception e) {
		super(erroCatalogado, e);
	}

	public SorteioApplicationException(ErroCatalogado erroCatalogado, String message) {
		super(erroCatalogado, message);
	}
	
	public SorteioApplicationException(ErroCatalogado erroCatalogado) {
		super(erroCatalogado, erroCatalogado.getDescricao());
	}


	
}
