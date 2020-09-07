 package com.wslogix.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wslogix.model.Cliente;
import com.wslogix.service.ClienteService;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cliente> findById(@PathVariable String id) {
		Cliente obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	//ex: http://localhost:8080/empresa/codigo?codigo=01
	
	@RequestMapping(value="/codigo", method=RequestMethod.GET)
	public ResponseEntity<Cliente> findByCodigo(
			@RequestParam(value="codigo", defaultValue="") String codigo) {
		
		Cliente obj = service.findByCodigo(codigo);
		return ResponseEntity.ok().body(obj);
	}

	//http://localhost:8081/empresa/data?data=04/20/1956 - MM/dd/yyyy
	
	@RequestMapping(value="/cnpj", method=RequestMethod.GET)
	public ResponseEntity<List<Cliente>> listByCnpj(
			@RequestParam(value="cnpj", defaultValue="") String cnpj) {
				
		List<Cliente> list = service.listByCnpj(cnpj);
		return ResponseEntity.ok().body(list);
		
	}
		
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Cliente>> findAll() {
		List<Cliente> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

}
