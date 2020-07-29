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

import com.wslogix.dto.ProcessoDto;
import com.wslogix.model.Processo;
import com.wslogix.service.ProcessoService;

@RestController
@RequestMapping(value = "/processo")
public class ProcessoResource {


	@Autowired
	private ProcessoService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Processo> findById(@PathVariable Integer id) {
		
		Processo obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Processo>> findAll() {
					
		List<Processo> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value="/{perfil}/acessos", method=RequestMethod.GET)
	public ResponseEntity<List<Processo>> findProcessos(@PathVariable Integer perfil) {
		List<Processo> list = service.findProcessos(perfil);
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value="/{modulo}/processos", method=RequestMethod.GET)
	public ResponseEntity<List<Processo>> findByModulo(@PathVariable Integer modulo) {
		List<Processo> list = service.findByModulo(modulo);
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value="/processos", method=RequestMethod.GET)
	public ResponseEntity<List<Processo>> findNoAccess(
			@RequestParam(value="perfil", defaultValue="0") Integer perfil, 
			@RequestParam(value="modulo", defaultValue="0") Integer modulo)	{

		List<Processo> list = service.findNoAccess(perfil, modulo);
		return ResponseEntity.ok().body(list);
	}

	// http://localhost:8081/processo/acessos?modulo=4
	//O perfil será obtido a partir do usuário logado
	@RequestMapping(value="/acessos", method=RequestMethod.GET)
	public ResponseEntity<List<Processo>> findByPerfilAndModulo(
			@RequestParam(value="modulo", defaultValue="0") Integer modulo)	{

		List<Processo> list = service.findByPerfilAndModulo(modulo);
		return ResponseEntity.ok().body(list);
	}

	// http://localhost:8081/processo/page?linhas=100&pagina=2
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ProcessoDto>> findPage(
			@RequestParam(value="pagina", defaultValue="0") Integer pagina, 
			@RequestParam(value="linhas", defaultValue="24") Integer qtdLinha, 
			@RequestParam(value="ordem", defaultValue="titulo") String ordem, 
			@RequestParam(value="direcao", defaultValue="ASC") String direcao,
			@RequestParam(value="modulo", defaultValue="0") Integer modulo,
			@RequestParam(value="titulo", defaultValue="") String titulo,
			@RequestParam(value="situacao", defaultValue="") String situacao)	{
		
		Page<ProcessoDto> list = service.findPage(
				pagina, qtdLinha, ordem, direcao, titulo, situacao, modulo);
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ProcessoDto dto) {
		
		Processo obj = service.fromDTO(dto);
		obj = service.insert(obj);
		
		//Retorna status de inclusão (201) e a URI do registro incluido
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(
			@Valid @RequestBody ProcessoDto dto, @PathVariable Integer id) {
		
		service.update(dto, id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
