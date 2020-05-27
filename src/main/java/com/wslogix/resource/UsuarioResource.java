package com.wslogix.resource;

/*  
 * Como o obj Usuario possui um mapeamento com UsuarioEmpresa,
 * ao ler e devolver um ou uma lista usuários, também serão lidos 
 * e devolvidos as empresas de cada Usuario, se usarmos na devolução
 * a entidade Usuário do pacote domain.
 * Para resolver isso, vamos utilizar um DTO (Data Transfer Object)
 * O DTO deve conter somente os capos que deverão ser enregues na requisição
 */

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import com.wslogix.dto.SenhaDto;
import com.wslogix.dto.UsuarioDto;
import com.wslogix.model.Usuario;
import com.wslogix.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;

	//ex: http://localhost:8081/usuarios/10
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Usuario> findById(@PathVariable Integer id) {
		
		Usuario obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	//ex: http://localhost:8081/usuarios/codigo?codigo=ivo
	
	@RequestMapping(value="/codigo", method=RequestMethod.GET)
	public ResponseEntity<UsuarioDto> findByCodigo(
			@RequestParam(value="codigo", defaultValue="") String codigo) {
		
		Usuario obj = service.findByCodigo(codigo);
		UsuarioDto dto = service.fromOBJ(obj);
		return ResponseEntity.ok().body(dto);
	}

	//ex: http://localhost:8081/usuarios/email?email=ivo.jose@gmail.com
	
	@RequestMapping(value="/email", method=RequestMethod.GET)
	public ResponseEntity<UsuarioDto> findByEmail(
			@RequestParam(value="email", defaultValue="") String email) {
		Usuario obj = service.findByEmail(email);
		UsuarioDto dto = service.fromOBJ(obj);
		return ResponseEntity.ok().body(dto);
	}

	//ex: http://localhost:8081/usuarios/ivo/nome		
	
	@RequestMapping(value="/{codigo}/nome", method=RequestMethod.GET)
	public ResponseEntity<String> findNameFromCodig(@PathVariable String codigo) {
		
		Usuario obj = service.findNameFromCodigo(codigo);
		return ResponseEntity.ok().body(obj.getNome());
	}
	
	/*
	 * Para o parâmetro que não estiver constido na requisição.
	 * será considerado para ele o valor default.
	 */
	@RequestMapping(value="/parametros", method=RequestMethod.GET)
	public ResponseEntity<List<UsuarioDto>> findByParametros(
			@RequestParam(value="codigo", defaultValue="") String codigo,
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="cpf", defaultValue="") String cpf)	{
		
		List<Usuario> list = service.findByParametros(codigo,nome,cpf);
		List<UsuarioDto> listDto = 
				list.stream().map(obj -> new UsuarioDto(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UsuarioDto>> findAll() {
					
		List<Usuario> list = service.findAll();
		List<UsuarioDto> listDto = 
			list.stream().map(obj -> new UsuarioDto(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value="/popup", method=RequestMethod.GET)
	public ResponseEntity<List<Usuario>> popup() {
		List<Usuario> list = service.popup();
		return ResponseEntity.ok().body(list);
	}
	
	// http://localhost:8081/usuario/paginacao?linhas=100&pagina=2
		
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<UsuarioDto>> findPage(
			@RequestParam(value="pagina", defaultValue="0") Integer pagina, 
			@RequestParam(value="linhas", defaultValue="24") Integer qtdLinha, 
			@RequestParam(value="ordem", defaultValue="nome") String ordem, 
			@RequestParam(value="direcao", defaultValue="ASC") String direcao) {
		
		Page<Usuario> list = service.findPage(pagina, qtdLinha, ordem, direcao);
		Page<UsuarioDto> listDto = list.map(obj -> new UsuarioDto(obj));  
		return ResponseEntity.ok().body(listDto);
	}

	/*@Valid : intercepta o dto e faz as validações anotadas no mesmo
	 * Se não passar na validação, interrompe o fluxo e
	 * retorna as mensagens das anotações
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody UsuarioDto dto) {
		
		Usuario obj = service.fromDTO(dto);
		obj = service.insert(obj);
		
		//Retorna status de inclusão (201) e a URI do registro incluido
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(
			@Valid @RequestBody UsuarioDto dto, @PathVariable Integer id) {
		
		service.update(dto, id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method=RequestMethod.PATCH)
	public ResponseEntity<Void> altSenha(
			@Valid @RequestBody SenhaDto objDto) {

		//UserSS user = UserService.authenticated();
		//service.updatePassword(user.getUsername(), objDto.getNovaSenha());
		return ResponseEntity.noContent().build();
	}

}
