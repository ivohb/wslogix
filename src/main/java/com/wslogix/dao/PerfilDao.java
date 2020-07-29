package com.wslogix.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wslogix.model.Modulo;
import com.wslogix.model.Perfil;

@Repository
public interface PerfilDao extends JpaRepository<Perfil, Integer>  {
	
	@Transactional(readOnly=true)
	public Perfil findByNome(String nome);

	@Query("SELECT obj FROM Perfil obj "
			+ "WHERE obj.id = :id")
	public Perfil findByPerfil(@Param("id") Integer id);

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Perfil obj "
			+ " WHERE obj.nome LIKE %:nome%")
	public Page<Perfil> findPage(@Param("nome") String nome, Pageable pageRequest);

}
