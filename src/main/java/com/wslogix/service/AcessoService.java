package com.wslogix.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;

import com.wslogix.dao.AcessoDao;
import com.wslogix.dto.AcessoDto;
import com.wslogix.exception.ObjectNotFoundException;
import com.wslogix.model.Acesso;

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
		return dao.findAll();
	}

	public Page<Acesso> findPage(Integer pagina, Integer qtdLinha, 
			String ordem, String direcao, Integer perfil, Integer modulo) {

		List<String> lista = new ArrayList<String>();
		lista.add("perfil");
		lista.add("modulo");
		
		PageRequest pageRequest = PageRequest.of(pagina, qtdLinha, 
				JpaSort.unsafe(Direction.valueOf(direcao), lista));
				
		return dao.findPage(perfil, modulo, pageRequest);
	}

	public Acesso fromDTO(AcessoDto dto) {
		Acesso obj = new Acesso();
		obj.setPerfil(dto.getPerfil());
		obj.setProcesso(dto.getProcesso());
		return obj;
	}

	public Acesso insert(Acesso obj) {
		return dao.save(obj);
	}

	public Acesso update(AcessoDto dto, Integer id) {
		Acesso newObj = findById(id);
		newObj.setProcesso(dto.getProcesso());
		return dao.save(newObj);
	}

	public void delete(Integer id) {
		Acesso e = findById(id);
		dao.deleteById(e.getId());
	}

}

