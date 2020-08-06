package com.wslogix.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wslogix.model.EdiCliente;

@Repository
public interface EdiClienteDao extends JpaRepository<EdiCliente, Integer>  {
	
	@Transactional(readOnly=true)
	public List<EdiCliente> findByPedido(String pedido);

	@Transactional(readOnly=true)
	public List<EdiCliente> findByCliente(String cliente);

	@Transactional(readOnly=true)
	public List<EdiCliente> findByClienteAndPedido(String cliente, String pedido);

	@Transactional(readOnly=true)
	public List<EdiCliente> findByClienteAndPedidoAndProduto(
			String cliente, String pedido, String produto);

	@Transactional(readOnly=true)
	public EdiCliente findByEmpresaAndClienteAndPedidoAndProdutoAndSituacao(String empresa, 
			String cliente,	String pedido, String produto, String situacao);

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM EdiCliente obj "
			+ " WHERE obj.pedido LIKE %:pedido%"
			+ " AND obj.produto LIKE %:produto%"
			+ " AND obj.situacao LIKE %:situacao%")
	public Page<EdiCliente> findPage(@Param("pedido") String pedido, @Param("produto") String produto,
			@Param("situacao") String situacao, Pageable pageRequest);

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM EdiCliente obj "
			+ " WHERE obj.inclusao >= :inicio AND obj.inclusao <= :fim ")
	public List<EdiCliente> findByPeriodo(@Param("inicio") Date inicio, @Param("fim") Date fim);
	

}
