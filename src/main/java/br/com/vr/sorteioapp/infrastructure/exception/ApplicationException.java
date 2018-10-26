package br.com.vr.sorteioapp.infrastructure.exception;
import br.com.vr.sorteioapp.infrastructure.errors.ErroCatalogado;

public abstract class ApplicationException extends RuntimeException {
	private static final String MENSAGEM_NULA = "-";

	private static final long serialVersionUID = 1L;

	private final transient ErroCatalogado erroCatalogado;

	public ApplicationException(ErroCatalogado erroCatalogado) {
		this.erroCatalogado = erroCatalogado;
	}

	public ApplicationException(ErroCatalogado erroCatalogadoDeImportacao, String message, Throwable cause) {
		super(message, cause);
		this.erroCatalogado = erroCatalogadoDeImportacao;
	}

	public ApplicationException(ErroCatalogado erroCatalogadoDeImportacao, String message) {
		super(message);
		this.erroCatalogado = erroCatalogadoDeImportacao;
	}

	public ApplicationException(ErroCatalogado erroCatalogadoDeImportacao, Throwable cause) {
		super(cause);
		this.erroCatalogado = erroCatalogadoDeImportacao;
	}

	public ErroCatalogado getErroCatalogado() {
		return erroCatalogado;
	}
	
	@Override
	public String toString() {
		ErroCatalogado erroCat = getErroCatalogado();
		String mensagemExcecao = getMessageToString() ;
		Throwable causa = getCause();
		String causaStr = (causa == null)?this.getClass().toString():causa.toString();
		String descricao = erroCat.getDescricao().contains("%")?"":erroCat.getDescricao();
		return String.format("Erro: %s, codigo: %s, descricao: %s, Causa: %s, Mensagem: %s", erroCat,
				erroCat.getCodigo(), descricao, causaStr,  mensagemExcecao);
	}

	private String getMessageToString(){
		String message = super.getMessage();
		if(message == null){
			message = MENSAGEM_NULA;
		}
		return message;
	}
	
	@Override
	public String getMessage() {
		String message = super.getMessage();
		if(message == null){
			message = String.format("[Codigo:%s, Descricao:%s]", erroCatalogado.getCodigo(), erroCatalogado.getDescricao());
		}
		return message;
	}


}
