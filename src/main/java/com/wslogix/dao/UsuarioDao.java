package com.wslogix.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wslogix.model.Usuario;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Integer>  {
	
	@Transactional(readOnly=true)
	@Query("SELECT new Usuario(obj.nome) FROM Usuario obj "
			+ "WHERE obj.codigo = :codigo")
	public Usuario findNameFromCodigo(@Param("codigo") String codigo);

	@Transactional(readOnly=true)
	public Usuario findByCodigo(String codigo);

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Usuario obj "
			+ " WHERE obj.codigo LIKE %:codigo%"
			+ " AND obj.nome LIKE %:nome%"
			+ " AND obj.cpfCnpj LIKE %:cpfCnpj%")
	public List<Usuario> findByParametros(@Param("codigo") String codigo,
		@Param("nome") String nome, @Param("cpfCnpj") String cpfCnpj);
	
	@Transactional(readOnly=true)
	@Query("SELECT new Usuario(obj.codigo, obj.nome) FROM Usuario obj ")
	public List<Usuario> popup();

	@Modifying
	@Transactional(readOnly=false)
	@Query("UPDATE Usuario u SET senha = :senha WHERE codigo = :codigo ")
	public void updatePassword(
			@Param("codigo") String codigo, @Param("senha") String senha);
	
	@Transactional(readOnly=true)
	public Usuario findByEmail(String email);

	@Transactional(readOnly=true)
	public Usuario findByCpfCnpj(String cpfCnpj);

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Usuario obj WHERE obj.nome LIKE %:nome% ")
	public Page<Usuario> findByNomeContaining(@Param("nome") String nome, Pageable pageRequest );

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Usuario obj "
			+ " WHERE obj.codigo LIKE %:codigo%"
			+ " AND obj.nome LIKE %:nome%"
			+ " AND obj.pessoa LIKE %:pessoa%"
			+ " AND obj.cpfCnpj LIKE %:cpfCnpj%")
	public Page<Usuario> findPage(@Param("codigo") String codigo, @Param("nome") String nome, 
			@Param("pessoa") String pessoa, @Param("cpfCnpj") String cpfCnpj, Pageable pageRequest);

}
