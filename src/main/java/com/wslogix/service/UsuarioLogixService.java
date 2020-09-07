package com.wslogix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wslogix.dao.UsuarioLogixDao;
import com.wslogix.model.UsuarioLogix;

@Service
public class UsuarioLogixService {

	@Autowired
	private UsuarioLogixDao dao;

	public UsuarioLogix insert(UsuarioLogix obj) {
		obj = dao.save(obj);
		return obj;
	}

}
