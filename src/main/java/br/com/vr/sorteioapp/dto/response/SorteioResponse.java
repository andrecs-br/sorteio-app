package br.com.vr.sorteioapp.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SorteioResponse extends AbstractResponse{

    @JsonIgnore
    private OutputMessage outputMessage;
    private String nomeSorteado;

    public SorteioResponse(OutputMessage response, String nomeSorteado) {
        super(response);
        this.nomeSorteado = nomeSorteado;
    }
}
