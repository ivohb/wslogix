package com.ivohb.me.wslogix.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;

import com.ivohb.me.wslogix.validation.AcessoValidation;

@AcessoValidation
public class AcessoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@Min(1)
	private Integer perfil;	
	@Min(1)
	private Integer modulo;

	public AcessoDto() {	}

	public AcessoDto(Integer id, Integer perfil, Integer modulo) {
		this.id = id;
		this.perfil = perfil;
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

	public Integer getModulo() {
		return modulo;
	}

	public void setModulo(Integer modulo) {
		this.modulo = modulo;
	}
		
}
