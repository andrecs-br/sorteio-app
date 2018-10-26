package br.com.vr.sorteioapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PessoaSorteio {
	
	private String nome;
	private boolean jaSorteado;

}
