package br.com.vr.sorteioapp.infrastructure.exception;


import br.com.vr.sorteioapp.infrastructure.errors.ErroCatalogado;

public class SorteioParamValidationException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public SorteioParamValidationException(ErroCatalogado erroCatalogado, Exception e) {
		super(erroCatalogado, e);
	}

	public SorteioParamValidationException(ErroCatalogado erroCatalogado, String message) {
		super(erroCatalogado, message);
	}
	
	public SorteioParamValidationException(ErroCatalogado erroCatalogado) {
		super(erroCatalogado, erroCatalogado.getDescricao());
	}
	
	public static SorteioParamValidationException comErroCatalogadoFormatado(ErroCatalogado erroCatalogado, Object... param) {
		return new SorteioParamValidationException(erroCatalogado,String.format(erroCatalogado.getDescricao(), param[0], param[1]));
	}


	
}
