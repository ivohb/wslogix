package com.wslogix.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wslogix.dto.EstoqueDto;
import com.wslogix.model.Item;
import com.wslogix.service.ItemService;

@RestController
@RequestMapping(value = "/item")
public class ItemResource {

	@Autowired
	ItemService service;
	
	@RequestMapping(value="/key", method=RequestMethod.GET)
	public ResponseEntity<Item> findByKey(
			@RequestParam(value="empresa", defaultValue="") String empresa,
			@RequestParam(value="codigo", defaultValue="") String codigo)	{

		Item obj = service.findByKey(empresa, codigo);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value="/id", method=RequestMethod.GET)
	public ResponseEntity<Item> findById(
			@RequestParam(value="empresa", defaultValue="") String empresa,
			@RequestParam(value="codigo", defaultValue="") String codigo)	{
		
		Item obj = service.findById(empresa, codigo);		
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value="/estoque/lote", method=RequestMethod.GET)
	public ResponseEntity<List<EstoqueDto>> estoqLote() {

		List<EstoqueDto> list = service.estoqLote();
		return ResponseEntity.ok().body(list);
	}

}
