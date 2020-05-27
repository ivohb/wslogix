package com.wslogix.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	//Lista dos campos com suas mensagens de erro de validação
	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Long timestamp, Integer status, 
			String error, String message, String path) {
		
		super(timestamp, status, error, message, path);
	}

	//getteres and setteres
	
	public List<FieldMessage> getErrors() {
		return errors;
	}

	/*Ao invés do setErrors que adiciona uma lista de erro,
	 * vamos adiconar um erro por vez.	
	 */
	public void addError(String fieldName, String messagem) {
		errors.add(new FieldMessage(fieldName, messagem));
	}
}
