package com.wslogix.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wslogix.model.ClienteItem;
import com.wslogix.service.ClienteItemService;

@RestController
@RequestMapping(value = "/cliente/item")
public class ClienteItemResource {

	@Autowired
	ClienteItemService service;
	
	@RequestMapping(value="/key", method=RequestMethod.GET)
	public ResponseEntity<ClienteItem> findByKey(
			@RequestParam(value="empresa", defaultValue="") String empresa,
			@RequestParam(value="cliente", defaultValue="") String cliente,
			@RequestParam(value="item", defaultValue="") String item)	{

		ClienteItem obj = service.findByKey(empresa, cliente, item);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value="/id", method=RequestMethod.GET)
	public ResponseEntity<ClienteItem> findById(
			@RequestParam(value="empresa", defaultValue="") String empresa,
			@RequestParam(value="cliente", defaultValue="") String cliente,
			@RequestParam(value="item", defaultValue="") String item)	{

		ClienteItem obj = service.findByKey(empresa, cliente, item);
		return ResponseEntity.ok().body(obj);
	}

	//http://localhost:8081/cliente/item/item?cliente=1&item=003
	
	@RequestMapping(value="/item/cliente", method=RequestMethod.GET)
	public ResponseEntity<List<ClienteItem>> findByClienteAnditemCliente(
			@RequestParam(value="empresa", defaultValue="") String empresa,
			@RequestParam(value="cliente", defaultValue="") String cliente,
			@RequestParam(value="itemCliente", defaultValue="") String itemCliente)	{

		List<ClienteItem> obj = service.findByClienteAnditemCliente(empresa, cliente, itemCliente);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value="/qtd/registro", method=RequestMethod.GET)
	public Integer countByEmpresa(
			@RequestParam(value="empresa", defaultValue="") String empresa) {

		return service.countByEmpresa(empresa);
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteItem>> findAll() {
		List<ClienteItem> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value="/empresa", method=RequestMethod.GET)
	public ResponseEntity<List<ClienteItem>> findByEmpresa(
			@RequestParam(value="empresa", defaultValue="") String empresa)	{

		List<ClienteItem> list = service.findByEmpresa(empresa);
		return ResponseEntity.ok().body(list);
	}

}
