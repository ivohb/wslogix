package com.ivohb.me.wslogix.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ivohb.me.wslogix.model.Usuario;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Integer>  {
	
	@Transactional(readOnly=true)
	@Query("SELECT new Usuario(obj.nome) FROM Usuario obj "
			+ "WHERE obj.codigo = :codigo")
	public Usuario findNameFromCodigo(@Param("codigo") String codigo);

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Usuario obj "
			+ "WHERE obj.codigo = :codigo")
	public Usuario findByLogin(@Param("codigo") String codigo);

	@Transactional(readOnly=true)
	public Usuario findByCodigo(String codigo);

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Usuario obj "
			+ " WHERE obj.codigo LIKE %:codigo%"
			+ " AND obj.nome LIKE %:nome%"
			+ " AND obj.cpf LIKE %:cpf%")
	public List<Usuario> findByParametros(@Param("codigo") String codigo,
		@Param("nome") String nome, @Param("cpf") String cpf);

	/*@Transactional(readOnly=true)
	@Query("SELECT new Usuario(u1.codigo, u1.nome) FROM Usuario u1 "
		+ " WHERE (u1.codigo = :codigo OR u1.codigo IN " 
		+ " (SELECT d.dependente FROM UsuarioDependente d WHERE d.usuario = :codigo))")
	public List<Usuario> findAprovador(@Param("codigo") String codigo);
 	*/
	
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
	public Usuario findByCpf(String cpf);

}
