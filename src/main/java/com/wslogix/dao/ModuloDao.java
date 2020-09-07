package com.wslogix.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wslogix.model.Modulo;
import com.wslogix.model.Usuario;

@Repository
public interface ModuloDao extends JpaRepository<Modulo, Integer>  {
	
	@Transactional(readOnly=true)
	public Modulo findByPath(String path);

	@Transactional(readOnly=true)
	public Modulo findByTitulo(String titulo);

	@Query("SELECT obj FROM Modulo obj "
			+ "WHERE obj.id = :id AND situacao = 'A'")
	public Modulo findByModulo(@Param("id") Integer id);

	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT m FROM Modulo m, Acesso a, Processo p "
			+ " WHERE p.id = a.processo AND a.perfil = :perfil "
			+ " AND p.modulo = m.id AND p.situacao = 'A' AND m.situacao = 'A'"
			+ " ORDER BY m.titulo")
	public List<Modulo> findModulos(@Param("perfil") Integer perfil);

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Modulo obj "
			+ " WHERE obj.titulo LIKE %:titulo%"
			+ " AND obj.situacao LIKE %:situacao%")
	public Page<Modulo> findPage(@Param("titulo") String titulo, 
			@Param("situacao") String situacao, Pageable pageRequest);

}
