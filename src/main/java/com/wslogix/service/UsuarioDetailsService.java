package com.wslogix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wslogix.dao.UsuarioDao;
import com.wslogix.model.Usuario;
import com.wslogix.security.UsuarioSS;


@Service
public class UsuarioDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioDao dao;

	/*Busca usuário pelo codigo de login e retorna um Usuario Spring Security*/

	@Override
	public UserDetails loadUserByUsername(String codigo) throws UsernameNotFoundException {
		Usuario obj = dao.findByCodigo(codigo);
		if (obj == null) {
			throw new UsernameNotFoundException(codigo);
		}
		return new UsuarioSS(obj.getId(), obj.getCodigo(), obj.getSenha(),obj.getPerfil());
	}
}
