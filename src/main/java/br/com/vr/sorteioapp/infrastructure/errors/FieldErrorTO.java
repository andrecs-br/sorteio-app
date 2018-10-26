package br.com.vr.sorteioapp.infrastructure.errors;

public class FieldErrorTO {

	private String campo;
	private String mensagem;

	public FieldErrorTO(String field, String message) {
		this.campo = field;
		this.mensagem = message;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
