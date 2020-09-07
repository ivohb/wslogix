package com.wslogix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wslogix.dao.ModuloDao;
import com.wslogix.dto.ModuloDto;
import com.wslogix.exception.ObjectNotFoundException;
import com.wslogix.model.Modulo;
import com.wslogix.util.Biblioteca;

@Service
public class ModuloService {

	@Autowired
	private ModuloDao dao;
	
	private Biblioteca bib = new Biblioteca();
	
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

	public Page<Modulo> findPage(Integer pagina, Integer qtdLinha, 
			String ordem, String direcao, String titulo, String situacao) {
		
		PageRequest pageRequest = PageRequest.of(pagina, qtdLinha, 
				Direction.valueOf(direcao), ordem);
		return dao.findPage(titulo,situacao, pageRequest);
	}

	public List<Modulo> findModulos(Integer perfil) {
		return dao.findModulos(perfil);
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
		obj.setId(null);		
		obj.setIcone(bib.blank(obj.getIcone()));
		obj.setPath(bib.blank(obj.getPath()));
		obj = dao.save(obj);
		return obj;
	}

	public Modulo update(ModuloDto dto, Integer id) {
		Modulo newObj = findById(id);
		if (!(dto.getSituacao().equalsIgnoreCase("I")))  {			
			newObj.setSituacao(dto.getSituacao());
		}		
		newObj.setIcone(dto.getIcone());
		newObj.setTitulo(dto.getTitulo()); 
		newObj.setPath(dto.getPath()); 
		return dao.save(newObj);
	}

	public void delete(Integer id) {	
		Modulo obj = findById(id);
		if (obj.getSituacao().equalsIgnoreCase("I")) {
			dao.deleteById(obj.getId());
		}		
	}

}
