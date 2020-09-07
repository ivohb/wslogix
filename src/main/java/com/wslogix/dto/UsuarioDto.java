package com.wslogix.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.wslogix.model.Usuario;
import com.wslogix.validation.UsuarioValidation;

@UsuarioValidation
public class UsuarioDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotNull(message = "campo_obrigatorio")
	@Pattern(regexp="[FJ]", message = "conteudo_invalido")
	private String pessoa;

	@NotNull(message = "campo_obrigatorio")
	@Length(min=3, max=15, message="tamanho_campo_invalido")
	private String codigo;

	@NotNull(message = "campo_obrigatorio")
	@Length(min=3, max=30, message="tamanho_campo_invalido")
	private String nome;		

	//@JsonIgnore
	private String senha;

	@NotNull(message = "campo_obrigatorio")
	@Email(message="email_invalido")
	private String email;
		
	@NotNull(message = "campo_obrigatorio")
	private String cpfCnpj;
			
	@NotNull(message = "campo_obrigatorio")
	private Integer perfil;

	//@Pattern(regexp="\\(\\d{2}\\)\\d{4}-\\d{4}", message = "conteudo_invalido")
	//se deixar a validação, vai obrigar o usuário informar o telefone
	private String telefone;

	//@Pattern(regexp="\\(\\d{2}\\)\\d{5}-\\d{4}", message = "conteudo_invalido")

	private String celular;

	@Pattern(regexp="[AIS]", message = "conteudo_invalido")
	private String situacao;

	private String codigoErp; //código do usuario no ERP

	public UsuarioDto() { }
	
	public UsuarioDto(Usuario obj) {
		this.id = obj.getId();
		this.pessoa = obj.getPessoa();
		this.codigo = obj.getCodigo();
		this.nome = obj.getNome();
		this.senha = obj.getSenha();
		this.email = obj.getEmail();
		this.cpfCnpj = obj.getCpfCnpj();
		this.perfil = obj.getPerfil();
		this.telefone = obj.getTelefone();
		this.celular = obj.getCelular();
		this.situacao = obj.getSituacao();
		this.codigoErp = obj.getCodigoErp();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPessoa() {
		return pessoa;
	}

	public void setPessoa(String pessoa) {
		this.pessoa = pessoa;
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

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
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

	public String getCodigoErp() {
		return codigoErp;
	}

	public void setCodigoErp(String codigoErp) {
		this.codigoErp = codigoErp;
	}

}
