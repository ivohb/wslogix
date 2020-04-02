package com.ivohb.me.wslogix.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ivohb.me.wslogix.model.Modulo;

@Repository
public interface ModuloDao extends JpaRepository<Modulo, Integer>  {
	
	@Transactional(readOnly=true)
	public Modulo findByPath(String path);

	@Transactional(readOnly=true)
	public Modulo findByTitulo(String titulo);

	@Query("SELECT obj FROM Modulo obj "
			+ "WHERE obj.id = :id")
	public Modulo findByModulo(@Param("id") Integer id);

}
