package com.wslogix.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.wslogix.dao.ClienteItemDao;
import com.wslogix.dao.EdiClienteDao;
import com.wslogix.dto.EdiClienteDto;
import com.wslogix.exception.FieldMessage;
import com.wslogix.model.EdiCliente;
import com.wslogix.security.UsuarioSS;
import com.wslogix.service.ClienteService;
import com.wslogix.service.EdiClienteService;
import com.wslogix.service.UserSecurityService;

public class EdiClienteValidator 
		implements ConstraintValidator<EdiClienteValidation, EdiClienteDto> {
	
	@Autowired
	private HttpServletRequest request;	
	@Autowired
	private EdiClienteDao dao;
	@Autowired
	private ClienteItemDao ciDao;
	@Autowired
	private ClienteService cliService;
	@Autowired
	private EdiClienteService service;
	
	@Override
	public void initialize(EdiClienteValidation ann) {
	}

	@Override
	public boolean isValid(EdiClienteDto dto, ConstraintValidatorContext context) {
		
		EdiCliente obj = null;
		List<FieldMessage> list = new ArrayList<>();
		String situacao = "E";
		UsuarioSS user = UserSecurityService.authenticated();
        String cliente = cliService.findByCnpj(user.getCnpj());
        
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
			} else {
				situacao = obj.getSituacao();
			}
		}

		if (dto.getPedido() == null || dto.getPedido().isEmpty()) {
		} else {
			if (dto.getProduto() == null || dto.getProduto().isEmpty()) {
			} else {
				if (cliente.isEmpty()) {
					list.add(new FieldMessage("cliente", "objeto_nao_existe"));
				} else {//verifica se já existe uma lista em elaboração p/ o produto
					obj = dao.findByEmpresaAndClienteAndPedidoAndProdutoAndSituacao(
							dto.getEmpresa(), cliente, dto.getPedido(), dto.getProduto(), situacao);			
					if (obj != null) {
						if (uriId == 0) {
							list.add(new FieldMessage("produto", "objeto_ja_existe"));
						} else {
							if (uriId != obj.getId()) {
								list.add(new FieldMessage("produto", "objeto_ja_existe"));
							}
						}
					} else {//verifica se o produto é um item do cliente						
						if (ciDao.findByClienteAnditemCliente(
								dto.getEmpresa(), cliente, dto.getProduto()).size() == 0) {
							list.add(new FieldMessage("produto", "objeto_nao_existe"));
						} 
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

