package com.wslogix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wslogix.dao.ModuloDao;
import com.wslogix.dto.ModuloDto;
import com.wslogix.exception.ObjectNotFoundException;
import com.wslogix.model.Modulo;

@Service
public class ModuloService {

	@Autowired
	private ModuloDao dao;
	
	public Modulo findById(Integer id) {
		Optional<Modulo> obj = dao.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
			"Não encontrado! Id: " + id + ", Objeto: " + Modulo.class.getName()));
	}

	public Modulo findByTitulo(String titulo) {
		Modulo obj = dao.findByTitulo(titulo);

		if (obj == null) {
			throw new ObjectNotFoundException(
				"Não encontrado! Titulo: " + titulo + ", "
						+ "Objeto: " + Modulo.class.getName());
		}
		
		return obj;
	}

	public List<Modulo> findAll() {
		return dao.findAll();
	}

	public Modulo fromDTO(ModuloDto dto) {
		Modulo obj = new Modulo();
		obj.setTitulo(dto.getTitulo());
		obj.setIcone(dto.getIcone());
		obj.setPath(dto.getPath());
		obj.setSituacao("I");
		return obj;
	}

	public Modulo insert(Modulo obj) {
		obj.setId(0);		
		obj = dao.save(obj);
		return obj;
	}

	public Modulo update(ModuloDto dto, Integer id) {
		Modulo newObj = findById(id);
		newObj.setIcone(dto.getIcone());
		newObj.setPath(dto.getPath());
		if (!(dto.getSituacao().equalsIgnoreCase("I")))  {			
			newObj.setSituacao(dto.getSituacao());
		}		
		newObj.setTitulo(dto.getTitulo());
		newObj.setSituacao(dto.getSituacao()); 
		return dao.save(newObj);
	}

	public void delete(Integer id) {	
		Modulo u = findById(id);
		dao.deleteById(u.getId());
	}

}
