package com.wslogix.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.wslogix.validation.PerfilValidation;

@PerfilValidation
public class PerfilDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotNull(message = "campo_obrigatorio")
	@Length(min=3, max=25, message="tamanho_campo_invalido")
	private String nome;
	
	@Pattern(regexp="[AIS]", message = "conteudo_invalido")
	private String situacao;
	
	public PerfilDto() {}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
}
