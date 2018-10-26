package br.com.vr.sorteioapp.dto.response;

import java.io.Serializable;

import br.com.vr.sorteioapp.infrastructure.errors.ErroCatalogado;

public class OutputMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    private int code;
    private String message;

    public OutputMessage() {
    }

    public OutputMessage(String message) {
        this.message = message;
    }

    public OutputMessage(Integer code, String message) {
        if (code != null) {
            this.code = code;
        }

        this.message = message;
    }

    public OutputMessage(ErroCatalogado erroCatalogado) {
        this.code = erroCatalogado.getCodigoExterno();
        this.message = erroCatalogado.getDescricao();
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
