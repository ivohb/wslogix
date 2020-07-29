package com.wslogix.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wsprocesso")
public class Processo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 25, nullable = false)
	private String titulo;

	@Column(length = 25, unique = true, nullable = false)
	private String path;

	@Column(length = 50)
	private String icone;

	@Column(length = 1, nullable = false)
	private String situacao;

	private Integer modulo;

	public Processo() {}

	public Processo(Integer id, String titulo) {
		super();
		this.id = id;
		this.titulo = titulo;
	}

	public Processo(Integer id, String titulo, Integer modulo) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.modulo = modulo;
	}

	public Processo(Integer id, String titulo, String path, String icone) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.path = path;
		this.icone = icone;
	}

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

	public Integer getModulo() {
		return modulo;
	}

	public void setModulo(Integer modulo) {
		this.modulo = modulo;
	}
}
