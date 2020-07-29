package com.wslogix.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.wslogix.model.EdiCliente;
import com.wslogix.validation.EdiClienteValidation;

@EdiClienteValidation
public class EdiClienteDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotNull(message = "campo_obrigatorio")
	@Length(min=1, max=30, message="tamanho_campo_invalido")
	private String pedido;

	@NotNull(message = "campo_obrigatorio")
	@Length(min=1, max=30, message="tamanho_campo_invalido")
	private String produto;

	public EdiClienteDto() {}

	public EdiClienteDto(EdiCliente obj) {
		this.id = obj.getId();
		this.pedido = obj.getPedido();
		this.produto = obj.getProduto();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPedido() {
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}
	
}
