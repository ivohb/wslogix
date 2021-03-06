package com.wslogix.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wslogix.dto.ModuloDto;
import com.wslogix.model.Modulo;
import com.wslogix.service.ModuloService;

@RestController
@RequestMapping(value = "/modulo")
public class ModuloResource {


	@Autowired
	private ModuloService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Modulo> findById(@PathVariable Integer id) {
		
		Modulo obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Modulo>> findAll() {
					
		List<Modulo> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<Modulo>> findPage(
			@RequestParam(value="pagina", defaultValue="0") Integer pagina, 
			@RequestParam(value="linhas", defaultValue="24") Integer qtdLinha, 
			@RequestParam(value="ordem", defaultValue="titulo") String ordem, 
			@RequestParam(value="direcao", defaultValue="ASC") String direcao,
			@RequestParam(value="titulo", defaultValue="") String titulo,
			@RequestParam(value="situacao", defaultValue="") String situacao)	{
		
		Page<Modulo> list = service.findPage(
				pagina, qtdLinha, ordem, direcao, titulo, situacao);
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value="/{perfil}/acessos", method=RequestMethod.GET)
	public ResponseEntity<List<Modulo>> findModulos(@PathVariable Integer perfil) {
		List<Modulo> list = service.findModulos(perfil);
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ModuloDto dto) {
		
		Modulo obj = service.fromDTO(dto);
		obj = service.insert(obj);
		
		//Retorna status de inclusão (201) e a URI do registro incluido
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(
			@Valid @RequestBody ModuloDto dto, @PathVariable Integer id) {
		
		service.update(dto, id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
