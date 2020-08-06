package com.wslogix.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wslogix.dto.EstoqueDto;
import com.wslogix.key.ItemKey;
import com.wslogix.model.Item;

@Repository                                           
public interface ItemDao extends JpaRepository<Item, ItemKey>  {
		
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Item obj "
			+ "WHERE obj.id.empresa = :empresa"
			+ " AND obj.id.codigo = :codigo")
	public Item findByKey(@Param("empresa") String empresa,
			@Param("codigo") String codigo);

	@Transactional(readOnly=true)
	@Query("SELECT new com.wslogix.dto.EstoqueDto("
			+ "e.item, e.local, e.saldo) "
			+ " FROM EstoqueLote e, Item i "
			+ "where e.empresa = i.id.empresa "
			+ " and e.item = i.id.codigo")			
	public List<EstoqueDto> estoqLote();

}
