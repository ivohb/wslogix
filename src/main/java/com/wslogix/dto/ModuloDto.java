package com.wslogix.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.wslogix.validation.ModuloValidation;

@ModuloValidation
public class ModuloDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotNull(message = "campo_obrigatorio")
	@Length(min=3, max=25, message="tamanho_campo_invalido")
	private String titulo;
	
	private String path;

	private String icone;
	
	@Pattern(regexp="[AIS]", message = "conteudo_invalido")
	private String situacao;

	public ModuloDto() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	
}
