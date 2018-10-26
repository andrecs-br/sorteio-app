package br.com.vr.sorteioapp.infrastructure.exception;


import br.com.vr.sorteioapp.infrastructure.errors.ErroCatalogado;

public class SorteioValidationException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public SorteioValidationException(ErroCatalogado catalogado, Exception e) {
		super(catalogado, e);
	}

	public SorteioValidationException(ErroCatalogado catalogado, String message) {
		super(catalogado, message);
	}
	
	public SorteioValidationException(ErroCatalogado catalogado) {
		super(catalogado, catalogado.getDescricao());
	}
	
	public static SorteioValidationException comErroCatalogadoFormatado(ErroCatalogado erroCatalogado, Object... param) {
		return new SorteioValidationException(erroCatalogado,String.format(erroCatalogado.getDescricao(), param[0], param[1]));
	}


	
}
