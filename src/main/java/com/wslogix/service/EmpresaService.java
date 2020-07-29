package com.wslogix.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wslogix.dao.EmpresaDao;
import com.wslogix.exception.ObjectNotFoundException;
import com.wslogix.model.Empresa;
import com.wslogix.util.Biblioteca;

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

	public List<Empresa> findByDate(Date data) {
		return dao.findByDate(data);
	}

	public void saveKey(String cnpj, String chave) {
		cnpj = "0"+cnpj;
		dao.saveKey(cnpj, chave);
	}

	public Empresa insert(Empresa obj) {
		obj = dao.save(obj);
		return obj;
	}

}
