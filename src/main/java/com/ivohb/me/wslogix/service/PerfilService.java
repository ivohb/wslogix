package com.ivohb.me.wslogix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivohb.me.wslogix.dao.PerfilDao;
import com.ivohb.me.wslogix.dto.PerfilDto;
import com.ivohb.me.wslogix.exception.ObjectNotFoundException;
import com.ivohb.me.wslogix.model.Perfil;

@Service
public class PerfilService {

	@Autowired
	private PerfilDao dao;
	
	public Perfil findById(Integer id) {
		Optional<Perfil> obj = dao.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
			"Não encontrado! Id: " + id + ", Objeto: " + Perfil.class.getName()));
	}

	public List<Perfil> findAll() {
		return dao.findAll();
	}

	public Perfil fromDTO(PerfilDto dto) {
		Perfil obj = new Perfil();
		obj.setNome(dto.getNome());
		obj.setSituacao("I");
		return obj;
	}

	public Perfil insert(Perfil obj) {
		obj.setId(0);		
		obj.setSituacao("I");
		obj = dao.save(obj);
		return obj;
	}

	public Perfil update(PerfilDto dto, Integer id) {
		Perfil newObj = findById(id);
		newObj.setNome(dto.getNome());
		if (!(dto.getSituacao().equalsIgnoreCase("I")))  {			
			newObj.setSituacao(dto.getSituacao());
		}		
		return dao.save(newObj);
	}

	public void delete(Integer id) {	
		Perfil u = findById(id);
		dao.deleteById(u.getId());
	}

}
