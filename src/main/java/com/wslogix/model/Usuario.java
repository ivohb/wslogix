package com.wslogix.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "wsusuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 15, unique = true, nullable = false)
	private String codigo;
	
	@Column(length = 30, nullable = false)	
	private String nome;
	
	@JsonIgnore //enibe retorno da senha no json
	@Column(length = 65)
	private String senha;
	
	@Column(length = 50, nullable = false)
	private String email;
	
	@Column(length = 14, nullable = false, unique = true)
	private String cpf;

	@Column(length = 1, nullable = false)
	private String sexo;

	@Column(nullable = false)
	private Integer perfil;

	@Column(length = 14)
	private String telefone;
	
	@Column(length = 14)
	private String celular;

	@Column(length = 1, nullable = false)
	private String situacao; //A/I/S
	
	public Usuario() {	}	
	
	public Usuario(String nome) {
		this.nome = nome;
	}	

	public Usuario(String codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public Integer getPerfil() {
		return perfil;
	}

	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", codigo=" + codigo + ", nome=" + nome 
			+ ", senha=" + senha + ", email=" + email + ", cpf=" + cpf 
			+ ", sexo=" + sexo + ", perfil=" + perfil + ", telefone=" + telefone 
			+ ", celular=" + celular + ", situacao=" + situacao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
