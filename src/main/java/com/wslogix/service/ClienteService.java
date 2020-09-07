package com.wslogix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wslogix.dao.ClienteDao;
import com.wslogix.exception.ObjectNotFoundException;
import com.wslogix.model.Cliente;

@Service
public class ClienteService {

	@Value("${emp.auth.raiz}")
	private String raiz;

	@Autowired
	private ClienteDao dao;

	public Cliente findById(String id) {
		Optional<Cliente> obj = dao.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
			"Não encontrado! Id: " + id + ", Objeto: " + Cliente.class.getName()));
	}

	public Cliente findByCodigo(String codigo) {
		Cliente obj = dao.findByCodigo(codigo);

		if (obj == null) {
			throw new ObjectNotFoundException(
				"Não encontrado! Codigo: " + codigo + ", "
						+ "Objeto: " + Cliente.class.getName());
		}
		
		return obj;
	}

	public List<Cliente> findAll() {
		return dao.findAll();
	}
	
	//no logix, cpf/cnpj contém 19 dígitos 001.663.567/0000-19
	
	public String findByCnpj(String cnpj) {
		if (cnpj.length() < 19) { 
			cnpj = "0"+cnpj;
		}
		
		List<Cliente> clientes = dao.findByCnpj(cnpj); 
		
		for (Cliente cliente : clientes) {			
			return cliente.getId();
		}
		
		return "";
	}

	public List<Cliente> listByCnpj(String cnpj) {
		if (cnpj.length() < 19) { 
			cnpj = "0"+cnpj;
		}		
		return dao.findByCnpj(cnpj); 		
	}

}
