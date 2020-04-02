package com.ivohb.me.wslogix.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class UsuarioLogix implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cod_usuario", length = 8)
	private String id;

	@Column(name = "nom_funcionario", length = 30)
	private String nome;

	@Column(name = "e_mail", length = 65)
	private String email;

	@Column(name = "cod_empresa_padrao", length = 2)
	private String empresaPadrao;

	public  UsuarioLogix() {	}

	public  UsuarioLogix(String id, String nome) {	
		this.id = id;
		this.nome = nome;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmpresaPadrao() {
		return empresaPadrao;
	}

	public void setEmpresaPadrao(String empresaPadrao) {
		this.empresaPadrao = empresaPadrao;
	}
	
}
