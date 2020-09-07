package com.wslogix.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.wslogix.validation.AcessoValidation;

@AcessoValidation
public class AcessoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotNull
	private Integer perfil;	
	private String nome;
	@NotNull
	private Integer processo;
	private String titulo;
	private String modulo;
	
	public AcessoDto() {	}

	public AcessoDto(Integer id, Integer perfil, 
			Integer processo, String nome, String titulo, String modulo) {
		this.id = id;
		this.perfil = perfil;
		this.processo = processo;
		this.nome = nome;
		this.titulo = titulo;
		this.modulo = modulo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPerfil() {
		return perfil;
	}

	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}

	public Integer getProcesso() {
		return processo;
	}

	public void setProcesso(Integer processo) {
		this.processo = processo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

}
