package br.com.vr.sorteioapp.infrastructure.enums;

import br.com.vr.sorteioapp.infrastructure.errors.ErroCatalogado;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumValidacao implements ErroCatalogado {

    SUCESSO(0,"SORTEIO-000","Operação realizada com sucesso"),
    ERRO_INTERNO(-99, "SORTEIO-099", "Erro interno");

    private Integer codigoInterno;
    private String codigo;
    private String descricao;

    @Override
    public Integer getCodigoExterno() {
    	return codigoInterno;
    }
}
