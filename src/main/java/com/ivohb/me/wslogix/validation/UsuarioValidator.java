package com.ivohb.me.wslogix.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.ivohb.me.wslogix.dao.PerfilDao;
import com.ivohb.me.wslogix.dao.UsuarioDao;
import com.ivohb.me.wslogix.dao.UsuarioLogixDao;
import com.ivohb.me.wslogix.dto.UsuarioDto;
import com.ivohb.me.wslogix.exception.FieldMessage;
import com.ivohb.me.wslogix.model.Perfil;
import com.ivohb.me.wslogix.model.Usuario;
import com.ivohb.me.wslogix.model.UsuarioLogix;
import com.ivohb.me.wslogix.service.UsuarioService;


public class UsuarioValidator 
		implements ConstraintValidator<UsuarioValidation, UsuarioDto> {

	// Através desse objeto, podemos pegar parãmetros 
	//envados na URI da requisição
	@Autowired
	private HttpServletRequest request;	

	@Autowired
	private UsuarioDao dao;
	@Autowired
	private UsuarioLogixDao userLogDao;
	@Autowired
	private PerfilDao perfilDao;
	@Autowired
	private UsuarioService service;
	
	@Override
	public void initialize(UsuarioValidation ann) {
		//função executada na inicialização da clasee
	}

	@Override
	public boolean isValid(UsuarioDto dto, ConstraintValidatorContext context) {
		
		UsuarioLogix userLogix = null;
		Usuario obj = null;
		
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
			uriId = Integer.parseInt(map.get("id")); //alteração de usuário
		} catch (Exception e) {
			uriId = 0; //inclusão de usuário
		}

		if (uriId > 0) {
			obj = service.findById(uriId);
			if (obj == null) {
				list.add(new FieldMessage("id", "objeto_nao_existe"));
			}
		}

		if (dto.getCodigo() == null || dto.getCodigo().isEmpty() ) {
		} else {
			userLogix = userLogDao.findByCodigo(dto.getCodigo());
			if (userLogix == null) {
				list.add(new FieldMessage("codigo", "usuario_nao_existe_no_rp"));
			} else {
				if (uriId == 0) {
					obj = dao.findByCodigo((dto.getCodigo()));
					if (obj != null) {
						list.add(new FieldMessage("codigo", "objeto_ja_existe"));
					}
				}
			}
		}

		if (dto.getCpf() == null || dto.getCpf().isEmpty()) {
		} else {
			obj = dao.findByCpf(dto.getCpf());				
			if (obj != null) {
				if (uriId == 0) {
					list.add(new FieldMessage("cpf", "objeto_ja_existe"));
				} else {
					if (uriId != obj.getId()) {
						list.add(new FieldMessage("cpf", "objeto_ja_existe"));
					}					
				}
			}
		}

		if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
		} else {
			obj = dao.findByEmail(dto.getEmail());				
			if (obj != null) {
				if (uriId == 0) {
					list.add(new FieldMessage("email", "objeto_ja_existe"));
				} else {
					if (uriId != obj.getId()) {
						list.add(new FieldMessage("email", "objeto_ja_existe"));
					}					
				}
			}
		}

		if (dto.getPerfil() == null || dto.getPerfil() == 0) {
		} else {
			Perfil perfil = perfilDao.findByPerfil(dto.getPerfil());
			if (perfil == null) {
				list.add(new FieldMessage("perfil", "objeto_nao_existe"));
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
