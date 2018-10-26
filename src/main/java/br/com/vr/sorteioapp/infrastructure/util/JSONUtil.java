package br.com.vr.sorteioapp.infrastructure.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JSONUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JSONUtil.class);
	
	public static String toJSON(Object o) {
		try {
			return new ObjectMapper().writeValueAsString(o);
		} catch (JsonProcessingException e) {
			LOGGER.error(e.getMessage());
			LOGGER.warn("Erro ao gerar JSon utilizando 'fasterxml.jackson' ObjectMapper. Tentando gerar utilizando GSon...");
		    return null; 
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T extends Object> T asObject(String json, Class clazz) throws Exception {
		return (T) new ObjectMapper().convertValue(json, clazz) ;
	}

}
