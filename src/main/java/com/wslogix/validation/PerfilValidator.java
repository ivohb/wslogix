package com.wslogix.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.wslogix.dao.PerfilDao;
import com.wslogix.dto.PerfilDto;
import com.wslogix.exception.FieldMessage;
import com.wslogix.model.Perfil;
import com.wslogix.service.PerfilService;

public class PerfilValidator 
		implements ConstraintValidator<PerfilValidation, PerfilDto> {

	/* Através desse objeto, podemos pegar parãmetros
	 * envados na URI da requisição
	 */	
	
	@Autowired
	private HttpServletRequest request;	
	@Autowired
	private PerfilDao dao;
	@Autowired
	private PerfilService service;

	@Override
	public void initialize(PerfilValidation ann) {
	}

	@Override
	public boolean isValid(PerfilDto dto, ConstraintValidatorContext context) {
		
		Perfil obj = null;
		List<FieldMessage> list = new ArrayList<>();

		/* Obtendo o id enviado na URI da requisição 
		 * Os parâmetros da requisição são enviados em forma de um 
		 * objeto java.util.Map<String, String>*/
		
		@SuppressWarnings("unchecked")
		Map<String, String> map =  //monta o map com os parâmetros da requisição
			(Map<String, String>) request.getAttribute(
					HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		Integer uriId = null;
		
		try {
			uriId = Integer.parseInt(map.get("id"));
		} catch (Exception e) {
			uriId = 0;
		}
		
		if (uriId > 0) {
			obj = service.findById(uriId);
			if (obj == null) {
				list.add(new FieldMessage("id", "objeto_nao_existe"));
			}
		}
		
		if (dto.getNome() == null || dto.getNome().isEmpty()) {
		} else {
			obj = dao.findByNome(dto.getNome());				
			if (obj != null) {
				if (uriId == 0) {
					list.add(new FieldMessage("titulo", "objeto_ja_existe"));
				} else {
					if (uriId != obj.getId()) {
						list.add(new FieldMessage("titulo", "objeto_ja_existe"));
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

