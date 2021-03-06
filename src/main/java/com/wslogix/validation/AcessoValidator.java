package com.wslogix.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.wslogix.dao.AcessoDao;
import com.wslogix.dao.ProcessoDao;
import com.wslogix.dao.PerfilDao;
import com.wslogix.dto.AcessoDto;
import com.wslogix.exception.FieldMessage;
import com.wslogix.model.Acesso;
import com.wslogix.model.Processo;
import com.wslogix.model.Perfil;
import com.wslogix.service.AcessoService;

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
	private ProcessoDao processoDao;
	
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
			if (dto.getProcesso() == null || dto.getProcesso() == 0) {
			} else {
				Processo processo = processoDao.findByProcesso(dto.getProcesso());
				if (processo == null) {
					list.add(new FieldMessage("processo", "objeto_nao_existe"));
				}
				obj = dao.findByPerfilAndProcesso(dto.getPerfil(), dto.getProcesso());
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

