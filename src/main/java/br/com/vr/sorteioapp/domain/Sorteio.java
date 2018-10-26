package br.com.vr.sorteioapp.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="singleton")
public class Sorteio {

    private List<PessoaSorteio> listaPessoas = null;

    @PostConstruct
    private void carregaPessoasSorteio() {
    	listaPessoas = new ArrayList<>();
    	listaPessoas.add(new PessoaSorteio("Camila Rocha", false));
    	listaPessoas.add(new PessoaSorteio("Renato Capela", false));
    	listaPessoas.add(new PessoaSorteio("Vinicius Gabriel", false));
    	listaPessoas.add(new PessoaSorteio("Vitor Caio", false));
    	listaPessoas.add(new PessoaSorteio("Ciro Formenton", false));
    	listaPessoas.add(new PessoaSorteio("Guilherme Fioritti", false));
    	listaPessoas.add(new PessoaSorteio("Danillo Colaredello", false));
    	listaPessoas.add(new PessoaSorteio("Emanoel Caldas", false));
    	listaPessoas.add(new PessoaSorteio("Rafael Cerqueira", false));
    	listaPessoas.add(new PessoaSorteio("Fernando Cisne", false));
    	listaPessoas.add(new PessoaSorteio("Artur Zanardi", false));
    	listaPessoas.add(new PessoaSorteio("Athur Kato", false));
    	listaPessoas.add(new PessoaSorteio("Marcelo Santos Silva", false));
    	listaPessoas.add(new PessoaSorteio("Karla Simões", false));
    	listaPessoas.add(new PessoaSorteio("Paulo Rodrigues", false));
    	listaPessoas.add(new PessoaSorteio("Deborah Zapata", false));
    	listaPessoas.add(new PessoaSorteio("Leandro Martins dos Santos", false));
    	listaPessoas.add(new PessoaSorteio("Adriano Barreto", false));
    	listaPessoas.add(new PessoaSorteio("Norma Sakima", false));
    	listaPessoas.add(new PessoaSorteio("Italo Santos", false));
    	listaPessoas.add(new PessoaSorteio("Daniel Sahara", false));
    	listaPessoas.add(new PessoaSorteio("André Fernandes", false));
    	listaPessoas.add(new PessoaSorteio("Rubens Kenji", false));
    	listaPessoas.add(new PessoaSorteio("Marcelo Rezende", false));
    	listaPessoas.add(new PessoaSorteio("Estiver Hipólito", false));
    	listaPessoas.add(new PessoaSorteio("Edvaldo Santiago", false));
    	listaPessoas.add(new PessoaSorteio("Alex Simas", false));
    	listaPessoas.add(new PessoaSorteio("Mario Rezende", false));
    	listaPessoas.add(new PessoaSorteio("Paulo Talassi", false));
    	listaPessoas.add(new PessoaSorteio("Guilherme Burger", false));
    	listaPessoas.add(new PessoaSorteio("Freddy Mendoza", false));
    	listaPessoas.add(new PessoaSorteio("Fabricio Furtado", false));
    	listaPessoas.add(new PessoaSorteio("Daniel Rodrigues", false));
    	listaPessoas.add(new PessoaSorteio("Marcello Ferreira", false));
    	listaPessoas.add(new PessoaSorteio("Paulo Mizuno", false));
    	listaPessoas.add(new PessoaSorteio("Fabio Eiji", false));
    	listaPessoas.add(new PessoaSorteio("Luis Oliveira", false));
    	listaPessoas.add(new PessoaSorteio("Roberto Antunes", false));
    	listaPessoas.add(new PessoaSorteio("Cassio Kenji", false));
    	listaPessoas.add(new PessoaSorteio("Lucas Fernandes", false));
    	listaPessoas.add(new PessoaSorteio("Bruna Rosa", false));
    	//listaPessoas.add(new PessoaSorteio("Cintia Barbosa Pereira", false));
    	listaPessoas.add(new PessoaSorteio("Gustavo Lima", false));
    	listaPessoas.add(new PessoaSorteio("Camila Santos", false));
    }
    
    private boolean todosSorteados() {
    	boolean retorno = true;
    	for (PessoaSorteio pessoa:listaPessoas) {
    		if (!pessoa.isJaSorteado()) {
    			retorno = false;
    		}
    	}
    	return retorno;
    }
    
    public String sortear() {
    	Random random = new Random();
    	String nomeSorteado = null;
    	do {
	    	int x = random.nextInt(listaPessoas.size());
	    	if (!listaPessoas.get(x).isJaSorteado()) {
	    		listaPessoas.get(x).setJaSorteado(true);
	    		nomeSorteado = listaPessoas.get(x).getNome();
	    		if (todosSorteados()) {
	    			this.carregaPessoasSorteio();
	    		}
	    	}
    	} while (nomeSorteado == null);
    	return nomeSorteado;
    }

}


