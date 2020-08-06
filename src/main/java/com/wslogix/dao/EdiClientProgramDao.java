package com.wslogix.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wslogix.model.EdiClientProgram;

@Repository
public interface EdiClientProgramDao extends JpaRepository<EdiClientProgram, Integer>  {
	
	@Transactional(readOnly=true)
	public List<EdiClientProgram> findByEdiCliente(Integer ediCliente);

}
