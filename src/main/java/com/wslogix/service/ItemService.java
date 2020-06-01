package com.wslogix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wslogix.dao.ItemDao;
import com.wslogix.dto.EstoqueDto;
import com.wslogix.exception.ObjectNotFoundException;
import com.wslogix.key.ItemKey;
import com.wslogix.model.Item;

@Service
public class ItemService {

	@Autowired
	private ItemDao dao;

	public Item findById(String empresa, String codigo) {
		ItemKey id = new ItemKey(empresa, codigo);
		Optional<Item> obj = dao.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Não encontrado! key: " + empresa + "/"+ codigo + ", "
						+ "Objeto: " + Item.class.getName()));
	}

	public Item findByKey(String empresa, String codigo) {
		Item obj = dao.findByKey(empresa, codigo);

		if (obj == null) {
			throw new ObjectNotFoundException(
				"Não encontrado! key: " + empresa + "/"+ codigo + ", "
						+ "Objeto: " + Item.class.getName());
		}
		
		return obj;
	}

	public List<EstoqueDto> estoqLote( ) {
		return dao.estoqLote();
		
	}
}
