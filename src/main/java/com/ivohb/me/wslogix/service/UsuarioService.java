package com.ivohb.me.wslogix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ivohb.me.wslogix.dao.UsuarioDao;
import com.ivohb.me.wslogix.dto.UsuarioDto;
import com.ivohb.me.wslogix.exception.ObjectNotFoundException;
import com.ivohb.me.wslogix.model.Usuario;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDao dao;	

	@Autowired
	private EmailService emailService;

	@Value("${img.prefix.user.profile}")
	private String prefix;	
	
	@Value("${img.profile.size}")
	private Integer size;
	
	public Usuario findById(Integer id) {
		Optional<Usuario> obj = dao.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
			"Não encontrado! Id: " + id + ", Objeto: " + Usuario.class.getName()));
	}

	public Usuario findByCodigo(String codigo) {
		Usuario obj = dao.findByCodigo(codigo);

		if (obj == null) {
			throw new ObjectNotFoundException(
				"Não encontrado! Codigo: " + codigo + ", "
						+ "Objeto: " + Usuario.class.getName());
		}
		
		return obj;
	}

	public Usuario findByEmail(String email) {
		Usuario obj = dao.findByEmail(email);
		
		if (obj == null) {
			throw new ObjectNotFoundException(
				"Não encontrado! Email: " + email + ", "
						+ "Objeto: " + Usuario.class.getName());
		}
		
		return obj;
	}
	
	public Usuario findNameFromCodigo(String codigo) {
		Usuario obj = dao.findNameFromCodigo(codigo);
		
		if (obj == null) {
			throw new ObjectNotFoundException(
				"Não encontrado! codigo: " + codigo + ", "
						+ "Objeto: " + Usuario.class.getName());
		}
		
		return obj;
	}

	public List<Usuario> findByParametros(String codigo, String nome, String cpf) {
		return dao.findByParametros(codigo, nome, cpf);
	}

	public List<Usuario> findApovadores(String codigo) {
		return null;// dao.findAprovador(codigo);
	}

	public List<Usuario> findAll() {
		return dao.findAll();
	}

	public List<Usuario> popup() {
		return dao.popup();
	}

	// número da página, linhas por página, campo a ordenar, ASC/DESC

	public Page<Usuario> findPage(Integer pagina, Integer 
			qtdLinha, String ordem, String direcao) {
		
		PageRequest pageRequest = PageRequest.of(pagina, qtdLinha, 
				Direction.valueOf(direcao), ordem);
		return dao.findAll(pageRequest);
	}

	public Usuario fromDTO(UsuarioDto dto) {
		Usuario obj = new Usuario();
		obj.setCodigo(dto.getCodigo());
		obj.setCpf(dto.getCpf());
		obj.setEmail(dto.getEmail());
		obj.setNome(dto.getNome());
		obj.setSexo(dto.getSexo());
		obj.setPerfil(dto.getPerfil());
		obj.setTelefone(dto.getTelefone());
		obj.setCelular(dto.getCelular());
		obj.setSituacao(dto.getSituacao());
		return obj;
	}

	public UsuarioDto fromOBJ(Usuario obj) {
		return new UsuarioDto(obj);
	}

	public Usuario insert(Usuario obj) {
		String newPass = "xxxxx";
		obj.setSenha(newPass);
		obj.setSituacao("I");
		obj = dao.save(obj);
		emailService.sendNewPasswordEmail(obj, newPass);
		return obj;
	}

	public Usuario update(UsuarioDto dto, Integer id) {
		Usuario newObj = findById(id);
		newObj.setNome(dto.getNome());
		newObj.setEmail(dto.getEmail());
		newObj.setCpf(dto.getCpf());
		newObj.setSexo(dto.getSexo());
		
		if (!(dto.getSituacao().equalsIgnoreCase("I")))  {			
			newObj.setSituacao(dto.getSituacao());
		}				
		return dao.save(newObj);
	}

	public void updatePassword(String codigo, String senha) {
		dao.updatePassword(codigo, senha);
	}

	public void delete(Integer id) {	
		Usuario u = findById(id);
		if (u.getSituacao().equalsIgnoreCase("I")) {
			dao.deleteById(u.getId());
		}		
	}

}
