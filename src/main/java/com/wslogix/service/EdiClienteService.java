package com.wslogix.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wslogix.dao.EdiClienteDao;
import com.wslogix.dto.EdiClienteDto;
import com.wslogix.exception.ObjectNotFoundException;
import com.wslogix.model.EdiCliente;
import com.wslogix.security.UsuarioSS;

@Service
public class EdiClienteService {

	@Autowired
	private EdiClienteDao dao;
	@Autowired
	private ClienteService cliService;
	
	public EdiCliente findById(Integer id) {
		Optional<EdiCliente> obj = dao.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
			"Não encontrado! Id: " + id + ", Objeto: " + EdiCliente.class.getName()));
	}

	public List<EdiCliente> findAll() {
		return dao.findAll();
	}

	public List<EdiCliente> findByPeriodo(Date inicio, Date fim) {
		return dao.findByPeriodo(inicio, fim);
	}

	public Page<EdiCliente> findPage(Integer pagina, Integer qtdLinha, 
			String ordem, String direcao, String pedido, String produto, String situacao) {
		
		PageRequest pageRequest = PageRequest.of(pagina, qtdLinha, 
				Direction.valueOf(direcao), ordem);
		return dao.findPage(pedido, produto, situacao, pageRequest);
	}

	public EdiCliente fromDTO(EdiClienteDto dto) {
		UsuarioSS user = UserSecurityService.authenticated();
        String cliente = cliService. findByCnpj(user.getCnpj());
		EdiCliente obj = new EdiCliente();
		obj.setEmpresa(dto.getEmpresa());
		obj.setCliente(cliente);
		obj.setPedido(dto.getPedido());
		obj.setProduto(dto.getProduto());
		return obj;
	}

	public EdiCliente insert(EdiCliente obj) {
		UsuarioSS user = UserSecurityService.authenticated();
		obj.setId(null);				
		obj.setSituacao("E");
		obj.setInclusao(new Date());
		obj.setUsuario(user.getUsername());
		obj = dao.save(obj);
		return obj;
	}

	public EdiCliente update(EdiClienteDto dto, Integer id) {
		EdiCliente newObj = findById(id);
		newObj.setPedido(dto.getPedido());
		newObj.setProduto(dto.getProduto());
		return dao.save(newObj);
	}

	public void delete(Integer id) {	
		EdiCliente obj = findById(id);
		if (obj.getSituacao().equalsIgnoreCase("E")) {
			dao.deleteById(obj.getId());
		}		
	}

}
