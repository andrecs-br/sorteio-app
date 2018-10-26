package br.com.vr.sorteioapp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vr.sorteioapp.dto.response.OutputMessage;
import br.com.vr.sorteioapp.dto.response.SorteioResponse;
import br.com.vr.sorteioapp.infrastructure.enums.EnumValidacao;
import br.com.vr.sorteioapp.infrastructure.errors.DefaultErrorTO;
import br.com.vr.sorteioapp.service.SorteioService;

@RestController
@Api(tags = "API - Sorteio")
public class SorteioController {
	
	@Autowired
	private final SorteioService sorteioService;

	public SorteioController(SorteioService sorteioService){
		this.sorteioService = sorteioService;
	}

    @ApiOperation(value = "SorteioResponse",
            notes = "Retorna o SorteioResponse que representa o retorno do nome sorteado. ",
            response = SorteioResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sorteio realizado com sucesso.", response = SorteioResponse.class),
            @ApiResponse(code = 400, message = "Bad Request, parâmetros informados inválidos ou obrigatórios.", response = DefaultErrorTO.class),
            @ApiResponse(code = 500, message = "Erro interno do servidor.", response = DefaultErrorTO.class)})
	@GetMapping(value = "/sortear",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SorteioResponse> sortear() {

    	String nomeSorteado = sorteioService.sortear();
		return new ResponseEntity<>(
				new SorteioResponse(
						new OutputMessage(
								EnumValidacao.SUCESSO), 
								nomeSorteado), HttpStatus.OK);
	}

}
