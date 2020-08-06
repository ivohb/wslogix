package com.wslogix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wslogix.dao.EdiClientProgramDao;
import com.wslogix.dto.EdiClientProgramDto;
import com.wslogix.exception.ObjectNotFoundException;
import com.wslogix.model.EdiClientProgram;

@Service
public class EdiClientProgramService {

	@Autowired
	private EdiClientProgramDao dao;

	public EdiClientProgram findById(Integer id) {
		Optional<EdiClientProgram> obj = dao.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
			"Não encontrado! Id: " + id + ", Objeto: " + EdiClientProgram.class.getName()));
	}
	
	public List<EdiClientProgram> findAll() {
		return dao.findAll();
	}

	public List<EdiClientProgram> findByEdiCliente(Integer ediCliente) {
		return dao.findByEdiCliente(ediCliente);
	}

	public EdiClientProgram fromDTO(EdiClientProgramDto dto) {
		EdiClientProgram obj = new EdiClientProgram();
		obj.setData(dto.getData());
		obj.setEdiCliente(dto.getEdiCliente());
		obj.setQuantidade(dto.getQuantidade());
		obj.setTipo(dto.getTipo());
		return obj;
	}

	public EdiClientProgram insert(EdiClientProgram obj) {
		obj.setId(null);				
		obj = dao.save(obj);
		return obj;
	}

	public EdiClientProgram update(EdiClientProgramDto dto, Integer id) {
		EdiClientProgram newObj = findById(id);
		newObj.setData(dto.getData());
		newObj.setQuantidade(dto.getQuantidade());
		newObj.setTipo(dto.getTipo());
		return dao.save(newObj);
	}

	public void delete(Integer id) {	
		EdiClientProgram obj = findById(id);
		dao.deleteById(obj.getId());
	}

}
