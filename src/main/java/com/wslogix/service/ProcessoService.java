package com.wslogix.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wslogix.dao.ModuloDao;
import com.wslogix.dao.ProcessoDao;
import com.wslogix.dto.ProcessoDto;
import com.wslogix.exception.ObjectNotFoundException;
import com.wslogix.model.Modulo;
import com.wslogix.model.Processo;
import com.wslogix.security.UsuarioSS;

@Service
public class ProcessoService {

	@Autowired
	private ProcessoDao dao;
	@Autowired
	private ModuloDao mDao;

	public Processo findById(Integer id) {
		Optional<Processo> obj = dao.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
			"Não encontrado! Id: " + id + ", Objeto: " + Processo.class.getName()));
	}
	
	public List<Processo> findAll() {
		return dao.findAll();
	}

	public List<Processo> findByModulo(Integer modulo) {
		return dao.findByModulo(modulo);
	}

	public List<Processo> findNoAccess(Integer perfil, Integer modulo) {
		return dao.findNoAccess(perfil, modulo);
	}
	
	public List<Processo> findByPerfilAndModulo(Integer modulo) {	
		UsuarioSS user = UserSecurityService.authenticated();
		return dao.findByPerfilAndModulo(user.getPerfil(), modulo);
	}
	
	public List<Processo> findProcessos(Integer perfil) {
		
		List<Processo> processos = dao.findProcessos(perfil);
		List<Processo> retorno = new ArrayList<Processo>();
		int idModulo = 0;
		Modulo modulo = null;
		Processo processo = null;
		
		for (Processo p : processos) {
			
			if (idModulo != p.getId()) {
				idModulo = p.getId();
				modulo = mDao.findByModulo(idModulo);
				processo = new Processo();
				processo.setIcone(modulo.getIcone());
				processo.setPath(modulo.getPath());
				processo.setTitulo(modulo.getTitulo());
				retorno.add(processo);
			}
			processo = new Processo();
			processo.setIcone(p.getIcone());
			processo.setPath(p.getPath());
			processo.setTitulo(p.getTitulo());
			retorno.add(processo);						
		}
		
		return retorno;
	}

	public Page<ProcessoDto> findPage(Integer pagina, Integer qtdLinha, 
			String ordem, String direcao, String titulo, String situacao, Integer modulo) {
		
		PageRequest pageRequest = PageRequest.of(pagina, qtdLinha, 
				Direction.valueOf(direcao), ordem);
		return dao.findPage(titulo, situacao, modulo, pageRequest);
	}

	public Processo fromDTO(ProcessoDto dto) {
		Processo obj = new Processo();
		obj.setModulo(dto.getModulo());
		obj.setTitulo(dto.getTitulo());
		obj.setIcone(dto.getIcone());
		obj.setPath(dto.getPath());
		obj.setSituacao("I");
		return obj;
	}

	public Processo insert(Processo obj) {
		obj.setId(null);				
		obj = dao.save(obj);
		return obj;
	}

	public Processo update(ProcessoDto dto, Integer id) {
		Processo newObj = findById(id);
		if (!(dto.getSituacao().equalsIgnoreCase("I")))  {			
			newObj.setSituacao(dto.getSituacao());
		}		
		newObj.setIcone(dto.getIcone());
		newObj.setSituacao(dto.getSituacao()); 
		return dao.save(newObj);
	}

	public void delete(Integer id) {	
		Processo obj = findById(id);
		if (obj.getSituacao().equalsIgnoreCase("I")) {
			dao.deleteById(obj.getId());
		}		
	}

}
