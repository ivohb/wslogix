package com.wslogix.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wslogix.dto.ProcessoDto;
import com.wslogix.model.Processo;

@Repository
public interface ProcessoDao extends JpaRepository<Processo, Integer>  {
	
	@Transactional(readOnly=true)
	public Processo findByPath(String path);

	@Transactional(readOnly=true)
	public Processo findByModuloAndTitulo(Integer modulo, String titulo);

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Processo obj "
			+ "WHERE obj.id = :id")
	public Processo findByProcesso(@Param("id") Integer id);

	@Transactional(readOnly=true)
	public List<Processo> findByModulo(Integer modulo);

	@Transactional(readOnly=true)
	@Query("SELECT new Processo("
			+ " m.id, p.titulo, p.path, p.icone) "
			+ " FROM Processo p, Acesso a, Modulo m "
			+ " WHERE p.id = a.processo AND a.perfil = :perfil "
			+ " AND p.modulo = m.id AND p.situacao = 'A' AND m.situacao = 'A'"
			+ " ORDER BY m.id")
	public List<Processo> findProcessos(@Param("perfil") Integer perfil);

	@Transactional(readOnly=true)
	@Query("SELECT new Processo("
			+ " p.id, p.titulo, p.path, p.icone) "
			+ " FROM Processo p, Acesso a, Modulo m "
			+ " WHERE p.modulo = m.id AND p.situacao = 'A' AND m.situacao = 'A' "
			+ " AND p.modulo = :modulo AND p.id = a.processo AND a.perfil = :perfil "
			+ " ORDER BY p.id")
	public List<Processo> findByPerfilAndModulo(
			@Param("perfil") Integer perfil, @Param("modulo") Integer modulo);

	@Transactional(readOnly=true)
	@Query("SELECT new Processo(p.id, p.titulo) FROM Processo p, Perfil perfil "
		+ " WHERE p.modulo = :modulo AND p.situacao = 'A' AND perfil.id = :perfil "
		+ "  AND p.id NOT IN (SELECT a.processo FROM Acesso a WHERE a.perfil = :perfil) " 
		+ " ORDER BY p.titulo")
	public List<Processo> findNoAccess(
			@Param("perfil") Integer perfil, @Param("modulo") Integer modulo);
	   
	@Transactional(readOnly=true)
	@Query("SELECT new com.wslogix.dto.ProcessoDto(p.id, p.titulo, "
			+ " p.path, p.icone, p.situacao, p.modulo, m.titulo) "
			+ "FROM Processo p, Modulo m  WHERE p.modulo = m.id"
			+ " AND p.titulo LIKE %:titulo%"
			+ " AND p.situacao LIKE %:situacao%"
			+ " AND ((p.modulo = :modulo AND :modulo > 0) OR (1=1 AND :modulo = 0)) "
			+ " ORDER BY p.modulo")
	public Page<ProcessoDto> findPage(@Param("titulo") String titulo, 
			@Param("situacao") String situacao, @Param("modulo") Integer modulo, Pageable pageRequest);

}
