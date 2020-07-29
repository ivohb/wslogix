package com.wslogix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wslogix.dao.ClienteItemDao;
import com.wslogix.exception.ObjectNotFoundException;
import com.wslogix.key.ClienteItemKey;
import com.wslogix.model.ClienteItem;

@Service
public class ClienteItemService {

	@Autowired
	private ClienteItemDao dao;

	public ClienteItem findById(String empresa, String cliente, String item) {
		ClienteItemKey id = new ClienteItemKey(empresa, cliente, item);
		Optional<ClienteItem> obj = dao.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Não encontrado! key: " + empresa + "/"+ cliente + "/"+ item + ", "
						+ "Objeto: " + ClienteItem.class.getName()));
	}

	public ClienteItem findByKey(String empresa, String cliente, String item) {
		ClienteItem obj = dao.findByKey(empresa, cliente, item);

		if (obj == null) {
			throw new ObjectNotFoundException(
					"Não encontrado! key: " + empresa + "/"+ cliente + "/"+ item + ", "
							+ "Objeto: " + ClienteItem.class.getName());
		}
		
		return obj;
	}

	public ClienteItem findByClienteAnditemCliente(
			String empresa, String cliente, String itemCliente) {
		return dao.findByClienteAnditemCliente(empresa, cliente, itemCliente);		
	}

	public Integer countByEmpresa(String empresa) {
		return dao.countByEmpresa(empresa);		
	}

	
	
	public List<ClienteItem> findAll() {
		return dao.findAll();
	}

}
