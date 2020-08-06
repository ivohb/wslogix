package com.wslogix.resource;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wslogix.dto.EdiClienteDto;
import com.wslogix.dto.ModuloDto;
import com.wslogix.model.EdiCliente;
import com.wslogix.service.EdiClienteService;

@RestController
@RequestMapping(value = "/edi/cliente")
public class EdiClienteResource {

	@Autowired
	private EdiClienteService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<EdiCliente> findById(@PathVariable Integer id) {
		
		EdiCliente obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EdiCliente>> findAll() {
					
		List<EdiCliente> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value="/periodo", method=RequestMethod.GET)
	public ResponseEntity<List<EdiCliente>> findByPeriodo(
			@RequestParam(value="inicio") Date inicio,
			@RequestParam(value="fim") Date fim)	{

		List<EdiCliente> list = service.findByPeriodo(inicio, fim);
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody EdiClienteDto dto) {
		
		EdiCliente obj = service.fromDTO(dto);
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(
			@Valid @RequestBody EdiClienteDto dto, @PathVariable Integer id) {
		
		service.update(dto, id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
