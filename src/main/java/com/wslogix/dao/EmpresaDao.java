package com.wslogix.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wslogix.model.Empresa;

@Repository                                      //entidade tipo do id
public interface EmpresaDao extends JpaRepository<Empresa, String>  {

	//Na Entidade EmpresaLogix, o codigo foi mapeado como id
	
	@Transactional(readOnly=true)
	@Query("FROM Empresa obj WHERE obj.id = :codigo")
	public Empresa findByCodigo(@Param("codigo") String codigo);

	@Transactional(readOnly=true)
	@Query("SELECT new Empresa("
			+ "obj.id, obj.codinome, obj.nome, obj.cnpj, obj.chave) "
			+ "FROM Empresa obj WHERE obj.chave IS NOT NULL")
	public List<Empresa> findAuth();

	@Modifying
	@Transactional(readOnly=false)
	@Query("UPDATE Empresa obj SET chave = :chave WHERE cnpj = :cnpj ")
	public void saveKey(
			@Param("cnpj") String cnpj, @Param("chave") String chave);

	@Transactional(readOnly=true)
	public Empresa findByCnpj(String cnpj);
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Empresa obj "
			+ "WHERE obj.datFundacao >= :data ")
	public List<Empresa> findByDate(@Param("data") Date data);

}
