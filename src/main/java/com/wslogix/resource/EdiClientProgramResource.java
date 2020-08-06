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

import com.wslogix.dto.EdiClientProgramDto;
import com.wslogix.model.EdiClientProgram;
import com.wslogix.service.EdiClientProgramService;

@RestController
@RequestMapping(value = "/edi/client/program")
public class EdiClientProgramResource {


	@Autowired
	private EdiClientProgramService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<EdiClientProgram> findById(@PathVariable Integer id) {
		
		EdiClientProgram obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EdiClientProgram>> findAll() {
					
		List<EdiClientProgram> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value="/{ediCliente}/programacao", method=RequestMethod.GET)
	public ResponseEntity<List<EdiClientProgram>> findByEdiCliente(@PathVariable Integer ediCliente) {
		List<EdiClientProgram> list = service.findByEdiCliente(ediCliente);
		return ResponseEntity.ok().body(list);
	}

	// http://localhost:8081/edi/client/program/page?linhas=100&pagina=2
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<EdiClientProgramDto>> findPage(
			@RequestParam(value="pagina", defaultValue="0") Integer pagina, 
			@RequestParam(value="linhas", defaultValue="24") Integer qtdLinha, 
			@RequestParam(value="ordem", defaultValue="titulo") String ordem, 
			@RequestParam(value="direcao", defaultValue="ASC") String direcao,
			@RequestParam(value="ediCliente", defaultValue="0") Integer ediCliente,
			@RequestParam(value="tipo", defaultValue="") String tipo)	{
		
		/*Page<EdiClientProgramDto> list = service.findPage(
				pagina, qtdLinha, ordem, direcao, ediCliente, tipo);
		return ResponseEntity.ok().body(list);*/
		return null;
		
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody EdiClientProgramDto dto) {
		
		EdiClientProgram obj = service.fromDTO(dto);
		obj = service.insert(obj);
				
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(
			@Valid @RequestBody EdiClientProgramDto dto, @PathVariable Integer id) {
		
		service.update(dto, id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
