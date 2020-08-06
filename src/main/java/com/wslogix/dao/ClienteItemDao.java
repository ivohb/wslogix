package com.wslogix.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wslogix.key.ClienteItemKey;
import com.wslogix.model.ClienteItem;

@Repository                                         //entidade     tipo do id
public interface ClienteItemDao extends JpaRepository<ClienteItem, ClienteItemKey>  {
		
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM ClienteItem obj "
			+ "WHERE obj.id.empresa = :empresa"
			+ " AND obj.id.cliente = :cliente"
			+ " AND obj.id.item = :item")
	public ClienteItem findByKey(@Param("empresa") String empresa,
			@Param("cliente") String cliente, @Param("item") String item);

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM ClienteItem obj "
			+ "WHERE obj.id.empresa = :empresa"
			+ " AND obj.id.cliente = :cliente")
	public ClienteItem findByCliente(@Param("empresa") String empresa,
			@Param("cliente") String cliente);

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM ClienteItem obj "
			+ "WHERE obj.id.empresa = :empresa"
			+ " AND obj.id.cliente = :cliente"
			+ " AND itemCliente = :itemCliente ")
	public List<ClienteItem> findByClienteAnditemCliente(@Param("empresa") String empresa,
			@Param("cliente") String cliente, @Param("itemCliente") String itemCliente);

	@Transactional(readOnly=true)
	@Query("SELECT COUNT(obj) FROM ClienteItem obj "
			+ "WHERE obj.id.empresa = :empresa")
	public Integer countByEmpresa(@Param("empresa") String empresa);

}
