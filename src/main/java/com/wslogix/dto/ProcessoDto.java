package com.wslogix.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.wslogix.model.Processo;
import com.wslogix.validation.ProcessoValidation;

@ProcessoValidation
public class ProcessoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotNull(message = "campo_obrigatorio")
	@Length(min=3, max=25, message="tamanho_campo_invalido")
	private String titulo;

	@NotNull(message = "campo_obrigatorio")
	@Length(min=3, max=25, message="tamanho_campo_invalido")
	private String path;

	@NotNull(message = "campo_obrigatorio")
	@Length(min=5, max=50, message="tamanho_campo_invalido")
	private String icone;
	
	@Pattern(regexp="[AIS]", message = "conteudo_invalido")
	private String situacao;

	@NotNull
	private Integer modulo;

	private String descModulo;
	
	public ProcessoDto() {}

	public ProcessoDto(Integer id, String titulo, String path, String icone,
			String situacao, Integer modulo, String descModulo) {
		this.id = id;
		this.titulo = titulo;
		this.path = path;
		this.icone = icone;
		this.situacao = situacao;
		this.modulo = modulo;
		this.descModulo = descModulo;
	}
	
	public ProcessoDto(Processo obj) {
		this.id = obj.getId();
		this.titulo = obj.getTitulo();
		this.path = obj.getPath();
		this.icone = obj.getIcone();
		this.situacao = obj.getSituacao();
		this.modulo = obj.getModulo();
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

	public String getDescModulo() {
		return descModulo;
	}

	public void setDescModulo(String descModulo) {
		this.descModulo = descModulo;
	}

	
}
