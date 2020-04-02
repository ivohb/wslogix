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

import com.ivohb.me.wslogix.dto.PerfilDto;
import com.ivohb.me.wslogix.model.Perfil;
import com.ivohb.me.wslogix.service.PerfilService;

@RestController
@RequestMapping(value = "/perfil")
public class PerfilResource {


	@Autowired
	private PerfilService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Perfil> findById(@PathVariable Integer id) {
		
		Perfil obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Perfil>> findAll() {
					
		List<Perfil> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody PerfilDto dto) {
		
		Perfil obj = service.fromDTO(dto);
		obj = service.insert(obj);
		
		//Retorna status de inclusão (201) e a URI do registro incluido
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(
			@Valid @RequestBody PerfilDto dto, @PathVariable Integer id) {
		
		service.update(dto, id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
