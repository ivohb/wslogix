package com.ivohb.me.wslogix.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ivohb.me.wslogix.dto.AcessoDto;
import com.ivohb.me.wslogix.model.Acesso;
import com.ivohb.me.wslogix.service.AcessoService;

@RestController
@RequestMapping(value = "/acesso")
public class AcessoResource {

	@Autowired
	private AcessoService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Acesso> findById(@PathVariable Integer id) {
		Acesso obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Acesso>> findAll() {
		List<Acesso> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody AcessoDto dto) {
		
		Acesso obj = service.fromDTO(dto);
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(
			@Valid @RequestBody AcessoDto dto, @PathVariable Integer id) {
		
		service.update(dto, id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
