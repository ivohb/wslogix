package com.wslogix.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wslogix.key.ClienteItemKey;

@Entity
@Table(name = "cliente_item")
public class ClienteItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ClienteItemKey id = new ClienteItemKey();
	
	@Column(name = "cod_item_cliente", length = 30)
	private String itemCliente;

	@Column(name = "tex_complementar", length = 76)
	private String descricao;

	@Column(name = "aplicacao", length = 1)
	private String aplicacao;

	@Column(name = "unid_med", length = 3)
	private String unidMedida;

	@Column(name = "fator_conv", length = 3)
	private Double fatConverdao;

	public ClienteItem() {}
	
	public ClienteItem(String itemCliente, String descricao) {
		super();
		this.itemCliente = itemCliente;
		this.descricao = descricao;
	}

	public ClienteItemKey getId() {
		return id;
	}

	public void setId(ClienteItemKey id) {
		this.id = id;
	}

	public String getItemCliente() {
		return itemCliente;
	}

	public void setItemCliente(String itemCliente) {
		this.itemCliente = itemCliente;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(String aplicacao) {
		this.aplicacao = aplicacao;
	}

	public String getUnidMedida() {
		return unidMedida;
	}

	public void setUnidMedida(String unidMedida) {
		this.unidMedida = unidMedida;
	}

	public Double getFatConverdao() {
		return fatConverdao;
	}

	public void setFatConverdao(Double fatConverdao) {
		this.fatConverdao = fatConverdao;
	}

}
