package com.wslogix.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails; 

/*implementação da classe UserDetails do spring security,
 * para que o framework possa fazer a atenticação do usuário
 */
public class UsuarioSS implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String codigo;
	private String senha;
	private Integer perfil;
	
	public UsuarioSS() {
	}	

	public UsuarioSS(Integer id, String codigo, String senha, Integer perfil) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.senha = senha;
		this.perfil = perfil;
	}

	public Integer getId() {
		return id;
	}

	public Integer getPerfil() {
		return perfil;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return codigo;
	}

	@Override
	public boolean isAccountNonExpired() {
		//implementar logica, p/ ver se a conta está expirada
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		//implementar logica, p/ ver se usuário está bloquado
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		//implementar logica, p/ ver se as credencias não estão espiradas
		return true;
	}

	@Override
	public boolean isEnabled() {
		//implementar logica, p/ ver se usuário está ativo
		return true;
	}
	
}
