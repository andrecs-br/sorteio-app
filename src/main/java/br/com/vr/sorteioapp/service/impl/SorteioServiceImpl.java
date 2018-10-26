package br.com.vr.sorteioapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vr.sorteioapp.domain.Sorteio;
import br.com.vr.sorteioapp.service.SorteioService;

@Component
public class SorteioServiceImpl implements SorteioService {
	
	@Autowired
	Sorteio sorteio;
	
	@Override
	public String sortear() {
		return sorteio.sortear();
	}

}
