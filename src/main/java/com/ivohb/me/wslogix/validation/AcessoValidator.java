package com.ivohb.me.wslogix.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.ivohb.me.wslogix.dao.AcessoDao;
import com.ivohb.me.wslogix.dao.ModuloDao;
import com.ivohb.me.wslogix.dao.PerfilDao;
import com.ivohb.me.wslogix.dto.AcessoDto;
import com.ivohb.me.wslogix.exception.FieldMessage;
import com.ivohb.me.wslogix.model.Acesso;
import com.ivohb.me.wslogix.model.Modulo;
import com.ivohb.me.wslogix.model.Perfil;
import com.ivohb.me.wslogix.service.AcessoService;

public class AcessoValidator 
		implements ConstraintValidator<AcessoValidation, AcessoDto> {

	/* Através desse objeto, podemos pegar parãmetros
	 * envados na URI da requisição
	 */	
	
	@Autowired
	private HttpServletRequest request;	
	@Autowired
	private AcessoDao dao;
	@Autowired
	private AcessoService service;
	@Autowired
	private PerfilDao perfilDao;
	@Autowired
	private ModuloDao moduloDao;
	
	@Override
	public void initialize(AcessoValidation ann) {
	}

	@Override
	public boolean isValid(AcessoDto dto, ConstraintValidatorContext context) {
		
		Acesso obj = null;
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

		if (dto.getPerfil() == null || dto.getPerfil() == 0) {
		} else {
			Perfil perfil = perfilDao.findByPerfil(dto.getPerfil());
			if (perfil == null) {
				list.add(new FieldMessage("perfil", "objeto_nao_existe"));
			}
			if (dto.getModulo() == null || dto.getModulo() == 0) {
			} else {
				Modulo modulo = moduloDao.findByModulo(dto.getModulo());
				if (modulo == null) {
					list.add(new FieldMessage("modulo", "objeto_nao_existe"));
				}
				obj = dao.findByPerfilAndModulo(dto.getPerfil(), dto.getModulo());
				if (obj != null) { 
					list.add(new FieldMessage("modulo", "objeto_ja_existe"));
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

