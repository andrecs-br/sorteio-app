package br.com.vr.sorteioapp.infrastructure.exception;


import br.com.vr.sorteioapp.infrastructure.errors.ErroCatalogado;

public enum ErroCatalogadoSorteio implements ErroCatalogado {

	ERRO_INTERNO(-99, "PRZ-PED-099", "Erro interno"), /*-*/
	ERRO_PARAMETROS_NULOS(-2, "PRZ-PED-002", "Parâmetros não podem ser nulos."), /*-*/
	ERRO_PARAMETROS_INVALIDOS(-80, "PRZ-PED-080", "Campo inválido ou obrigatório."),
	ERRO_ERP_GATEWAY(-89, "PRZ-PED-089", "Erro ao chamar serviço de cálculo de dias úteis no ERP-GATEWAY."),
	ERRO_BUSCAR_CONFIG_COBRANCA(-90, "PRZ-PED-090", "Erro ao obter a configuração de cobrança na base."),
	ERRO_BUSCAR_CONFIG_AGENDAMENTO(-91, "PRZ-PED-091", "Erro ao buscar a configuração padrão.");

	private final Integer codigoInterno;
	private final String codigo;
	private final String descricao;

	ErroCatalogadoSorteio(Integer codigoInterno, String codigo, String descricao) {
		this.codigoInterno = codigoInterno;
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigoExterno() {
		return codigoInterno;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

}
