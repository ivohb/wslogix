package com.ivohb.me.wslogix.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.ivohb.me.wslogix.model.Usuario;
import com.ivohb.me.wslogix.validation.UsuarioValidation;

@UsuarioValidation
public class UsuarioDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotNull(message = "campo_obrigatorio")
	@Length(min=3, max=15, message="tamanho_campo_invalido")
	private String codigo;

	@NotNull(message = "campo_obrigatorio")
	@Length(min=3, max=30, message="tamanho_campo_invalido")
	private String nome;		

	@NotNull(message = "campo_obrigatorio")
	@Email(message="email_invalido")
	private String email;
	
	
	@NotNull(message = "campo_obrigatorio")
	@CPF(message = "cpf_invalido")
	private String cpf;
		
	@NotNull(message = "campo_obrigatorio")
	@Pattern(regexp="[MF]", message = "conteudo_invalido")
	private String sexo;
	
	@Min(1)
	private Integer perfil;

	//@Pattern(regexp="\\(\\d{2}\\)\\d{4}-\\d{4}", message = "conteudo_invalido")
	//se deixar a validação, vai obrigar o usuário informar o telefone
	private String telefone;

	//@Pattern(regexp="\\(\\d{2}\\)\\d{5}-\\d{4}", message = "conteudo_invalido")

	private String celular;

	@Pattern(regexp="[AIS]", message = "conteudo_invalido")
	private String situacao;

	public UsuarioDto() { }
	
	public UsuarioDto(Usuario obj) {
		this.id = obj.getId();
		this.codigo = obj.getCodigo();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
		this.cpf = obj.getCpf();
		this.sexo = obj.getSexo();
		this.perfil = obj.getPerfil();
		this.telefone = obj.getTelefone();
		this.celular = obj.getCelular();
		this.situacao = obj.getSituacao();
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
		return((telefone != null) ? telefone : "");
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return((celular != null) ? celular : "");

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
	
}
