package br.com.vr.sorteioapp.infrastructure.errors;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorTO {

	private final List<FieldErrorTO> campos = new ArrayList<>();

	public void addFieldError(String path, String message) {
		FieldErrorTO error = new FieldErrorTO(path, message);
		campos.add(error);
	}

	public List<FieldErrorTO> getCampos() {
		return campos;
	}
}
