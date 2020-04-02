package com.ivohb.me.wslogix.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ivohb.me.wslogix.model.Perfil;

@Repository
public interface PerfilDao extends JpaRepository<Perfil, Integer>  {
	
	@Transactional(readOnly=true)
	public Perfil findByNome(String nome);

	@Query("SELECT obj FROM Perfil obj "
			+ "WHERE obj.id = :id")
	public Perfil findByPerfil(@Param("id") Integer id);

}
