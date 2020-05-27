package com.wslogix.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.wslogix.dao.EmpresaDao;
import com.wslogix.dto.EmpresaKeyDto;
import com.wslogix.exception.FieldMessage;
import com.wslogix.model.Empresa;
import com.wslogix.util.Biblioteca;

public class EmpresaKeyValidator 
		implements ConstraintValidator<EmpresaKeyValidation, EmpresaKeyDto> {

	/* Através desse objeto, podemos pegar parãmetros
	 * envados na URI da requisição
	 */	
	
	@Autowired
	private EmpresaDao dao;
	
	@Override
	public void initialize(EmpresaKeyValidation ann) {
	}

	@Override
	public boolean isValid(EmpresaKeyDto dto, ConstraintValidatorContext context) {
		
		Empresa obj = null;
		List<FieldMessage> list = new ArrayList<>();

		if (dto.getChave() == null || dto.getChave().isEmpty()) {			
		} else {
			if (dto.getCnpj() == null || dto.getCnpj().isEmpty()) {
			} else {
				String cnpj = "0"+dto.getCnpj();
				obj = dao.findByCnpj(cnpj);
				if (obj == null) {
					list.add(new FieldMessage("cnpj", "objeto_inexistente"));
				} else {
					Biblioteca bib = new Biblioteca();
					String chave = bib.chaveProduto(dto.getCnpj());
					if (dto.getChave().equals(chave)) {					
					} else {
						list.add(new FieldMessage("cnpj", "empresa_key_invalida"));
					}
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

