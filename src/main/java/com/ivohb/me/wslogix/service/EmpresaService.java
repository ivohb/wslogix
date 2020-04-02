package com.ivohb.me.wslogix.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ivohb.me.wslogix.dao.EmpresaDao;
import com.ivohb.me.wslogix.exception.ObjectNotFoundException;
import com.ivohb.me.wslogix.model.Empresa;
import com.ivohb.me.wslogix.util.Biblioteca;

@Service
public class EmpresaService {

	@Value("${emp.auth.raiz}")
	private String raiz;

	@Autowired
	private EmpresaDao dao;
	
	public Empresa findByCodigo(String codigo) {
		Empresa obj = dao.findByCodigo(codigo);

		if (obj == null) {
			throw new ObjectNotFoundException(
				"Não encontrado! Codigo: " + codigo + ", "
						+ "Objeto: " + Empresa.class.getName());
		}
		
		return obj;
	}

	public List<Empresa> findAll() {
		return dao.findAll();
	}

	public List<Empresa> findAuth() {
		List<Empresa> lista = dao.findAuth();
		Biblioteca bib = new Biblioteca();
		List<Empresa> empresaAuth = new ArrayList<>();
		
		for (Empresa empresa : lista) {			
			String chave = bib.chaveProduto(empresa.getCnpj().substring(1));
			if (empresa.getChave().trim().equals(chave)) {	
				empresaAuth.add(empresa);
			}
		}
		
		return empresaAuth;
	}

	public void saveKey(String cnpj, String chave) {
		cnpj = "0"+cnpj;
		dao.saveKey(cnpj, chave);
	}

}
