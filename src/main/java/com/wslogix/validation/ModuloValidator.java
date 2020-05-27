package com.wslogix.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.wslogix.dao.ModuloDao;
import com.wslogix.dto.ModuloDto;
import com.wslogix.exception.FieldMessage;
import com.wslogix.model.Modulo;
import com.wslogix.service.ModuloService;

public class ModuloValidator 
		implements ConstraintValidator<ModuloValidation, ModuloDto> {
	
	@Autowired
	private HttpServletRequest request;	
	@Autowired
	private ModuloDao dao;
	@Autowired
	private ModuloService service;

	@Override
	public void initialize(ModuloValidation ann) {
	}

	@Override
	public boolean isValid(ModuloDto dto, ConstraintValidatorContext context) {
		
		Modulo obj = null;
		List<FieldMessage> list = new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		Map<String, String> map =  
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

		if (dto.getTitulo() == null || dto.getTitulo().isEmpty()) {
		} else {
			obj = dao.findByTitulo(dto.getTitulo());				
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

		if (dto.getPath() == null || dto.getPath().isEmpty()) {
		} else {
			obj = dao.findByTitulo(dto.getPath());				
			if (obj != null) {
				if (uriId == 0) {
					list.add(new FieldMessage("path", "objeto_ja_existe"));
				} else {
					if (uriId != obj.getId()) {
						list.add(new FieldMessage("path", "objeto_ja_existe"));
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

