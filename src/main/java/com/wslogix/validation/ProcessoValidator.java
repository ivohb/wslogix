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
import com.wslogix.dao.ProcessoDao;
import com.wslogix.dto.ProcessoDto;
import com.wslogix.exception.FieldMessage;
import com.wslogix.model.Modulo;
import com.wslogix.model.Processo;
import com.wslogix.service.ProcessoService;

public class ProcessoValidator 
		implements ConstraintValidator<ProcessoValidation, ProcessoDto> {
	
	@Autowired
	private HttpServletRequest request;	
	@Autowired
	private ProcessoDao dao;
	@Autowired
	private ProcessoService service;

	@Autowired
	private ModuloDao modDao;
	
	@Override
	public void initialize(ProcessoValidation ann) {
	}

	@Override
	public boolean isValid(ProcessoDto dto, ConstraintValidatorContext context) {
		
		Processo obj = null;
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

		if (dto.getModulo() == null || dto.getModulo() <= 0) {
			list.add(new FieldMessage("modulo", "conteudo_invalido"));
		}
		
		if (dto.getTitulo() == null || dto.getTitulo().isEmpty()) {
		} else {
			Modulo modulo = modDao.getOne(dto.getModulo());
			if (modulo == null) {
				list.add(new FieldMessage("modulo", "objeto_nao_existe"));
			} else {
				obj = dao.findByModuloAndTitulo(dto.getModulo(), dto.getTitulo());				
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
		}

		if (dto.getPath() == null || dto.getPath().isEmpty()) {
		} else {
			obj = dao.findByPath(dto.getPath());				
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

