package com.wslogix.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

//@EdiClientProgramValidation
public class EdiClientProgramDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer ediCliente;
	
	@NotNull(message = "campo_obrigatorio")
	@Pattern(regexp="[CP]", message = "conteudo_invalido")
	private String tipo;
	
	private Date data;

	private Double quantidade;

	public EdiClientProgramDto() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEdiCliente() {
		return ediCliente;
	}

	public void setEdiCliente(Integer ediCliente) {
		this.ediCliente = ediCliente;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	
	
}
