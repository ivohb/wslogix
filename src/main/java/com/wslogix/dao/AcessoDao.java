package com.wslogix.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wslogix.model.Acesso;

@Repository                                           
public interface AcessoDao extends JpaRepository<Acesso, Integer>  {

	@Transactional(readOnly=true)
	public Acesso findByPerfil(Integer perfil);

	@Transactional(readOnly=true)
	public Acesso findByModulo(Integer modulo);

	@Transactional(readOnly=true)
	public Acesso findByPerfilAndModulo(Integer perfil, Integer modulo);

}
