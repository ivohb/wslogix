package com.wslogix.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.wslogix.dto.SenhaDto;
import com.wslogix.exception.FieldMessage;

public class SenhaValidator 
		implements ConstraintValidator<Senha, SenhaDto> {

	@Override
	public void initialize(Senha ann) {
	}

	@Override
	public boolean isValid(SenhaDto objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if (objDto.getNovaSenha() == null || objDto.getNovaSenha().isEmpty()) {
		} else {
			if (objDto.getConfirmaSenha() == null || objDto.getConfirmaSenha().isEmpty()) {
			} else {
				if (objDto.getNovaSenha().equals(objDto.getConfirmaSenha())) {
					if (!ValidaSenha.isValidSenha(objDto.getNovaSenha())) {
						list.add(new FieldMessage("confirmaSenha", "senha_insegura"));
					}				
				} else {
					list.add(new FieldMessage("confirmaSenha", "confirma_senha_invalida"));
				}
			}
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(
					e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}		
		return list.isEmpty();
	}
}

