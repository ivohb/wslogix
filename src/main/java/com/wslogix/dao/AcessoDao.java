package com.wslogix.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wslogix.model.Acesso;

@Repository                                           
public interface AcessoDao extends JpaRepository<Acesso, Integer>  {

	@Transactional(readOnly=true)
	public Acesso findByPerfil(Integer perfil);

	@Transactional(readOnly=true)
	public Acesso findByProcesso(Integer processo);

	@Transactional(readOnly=true)
	public Acesso findByPerfilAndProcesso(Integer perfil, Integer processo);

	@Transactional(readOnly=true)
	@Query("SELECT new com.wslogix.dto.AcessoDto(a.id, a.perfil, a.processo, "
			+ " per.nome, pro.titulo, m.titulo as modulo) FROM Acesso a, Perfil per, Processo pro, Modulo m "
			+ " WHERE a.perfil = per.id AND a.processo = pro.id AND pro.modulo = m.id"
			+ " AND ((m.id = :modulo AND :modulo > 0) OR (1=1 AND :modulo = 0))"
			+ " AND ((a.perfil = :perfil AND :perfil > 0) OR (1=1 AND :perfil = 0))")
	public Page<Acesso> findPage(@Param("perfil") Integer perfil, 
			@Param("modulo") Integer modulo, Pageable pageRequest);

}
