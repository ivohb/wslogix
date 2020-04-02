package com.ivohb.me.wslogix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivohb.me.wslogix.dao.AcessoDao;
import com.ivohb.me.wslogix.dto.AcessoDto;
import com.ivohb.me.wslogix.exception.ObjectNotFoundException;
import com.ivohb.me.wslogix.model.Acesso;

@Service
public class AcessoService {

	@Autowired
	private AcessoDao dao;

	public Acesso findById(Integer id) {
		Optional<Acesso> obj = dao.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
			"Não encontrado! Id: " + id + ", Objeto: " + Acesso.class.getName()));		
	}

	public List<Acesso> findAll() {
		
		List<Acesso> list = dao.findAll();
		
		if (list.size() == 0) {
			throw new ObjectNotFoundException("dados_nao_encontrado");
		}
		
		return list;
	}

	public Acesso fromDTO(AcessoDto dto) {
		Acesso obj = new Acesso();
		obj.setPerfil(dto.getPerfil());
		obj.setModulo(dto.getModulo());
		return obj;
	}

	public Acesso insert(Acesso obj) {
		return dao.save(obj);
	}

	public Acesso update(AcessoDto dto, Integer id) {
		Acesso newObj = findById(id);
		newObj.setModulo(dto.getModulo());
		return dao.save(newObj);
	}

	public void delete(Integer id) {
		Acesso e = findById(id);
		dao.deleteById(e.getId());
	}

}

