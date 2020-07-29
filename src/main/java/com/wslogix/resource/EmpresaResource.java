package com.wslogix.resource;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wslogix.dto.EmpresaKeyDto;
import com.wslogix.model.Empresa;
import com.wslogix.service.EmpresaService;

@RestController
@RequestMapping(value = "/empresa")
public class EmpresaResource {

	@Autowired
	private EmpresaService service;
		
	//ex: http://localhost:8080/empresa/codigo?codigo=01
	
	@RequestMapping(value="/codigo", method=RequestMethod.GET)
	public ResponseEntity<Empresa> findByCodigo(
			@RequestParam(value="codigo", defaultValue="") String codigo) {
		
		Empresa obj = service.findByCodigo(codigo);
		return ResponseEntity.ok().body(obj);
	}

	//http://localhost:8081/empresa/data?data=04/20/1956 - MM/dd/yyyy
	
	@RequestMapping(value="/data", method=RequestMethod.GET)
	public ResponseEntity<List<Empresa>> findByDate(
			@RequestParam(value="data", defaultValue="01/31/1890") Date data) {
				
		List<Empresa> list = service.findByDate(data);
		return ResponseEntity.ok().body(list);
		
	}
		
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Empresa>> findAll() {
		List<Empresa> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value="/autorizada",method=RequestMethod.GET)
	public ResponseEntity<List<Empresa>> findAuth() {
		List<Empresa> list = service.findAuth();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(method=RequestMethod.PATCH)
	public ResponseEntity<Void> saveKey(
			@Valid @RequestBody EmpresaKeyDto dto) {

		service.saveKey(dto.getCnpj(), dto.getChave());
		return ResponseEntity.noContent().build();
	}

}
