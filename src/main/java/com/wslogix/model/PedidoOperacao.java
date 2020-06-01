package com.wslogix.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wslogix.key.PedItemNatKey;

@Entity
@Table(name = "ped_item_nat")
public class PedidoOperacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PedItemNatKey id = new PedItemNatKey();
	@Column(name = "ies_separa_nff", length = 1)
	private String separaNf;
	@Column(name = "cod_cliente", length = 15)
	private String cliente;
	@Column(name = "cod_nat_oper")
	private Integer natOperacao;
	@Column(name = "cod_cnd_pgto")
	private Double codPgto;

	public PedidoOperacao() { }

	public PedItemNatKey getId() {
		return id;
	}

	public void setId(PedItemNatKey id) {
		this.id = id;
	}

	public String getSeparaNf() {
		return separaNf;
	}

	public void setSeparaNf(String separaNf) {
		this.separaNf = separaNf;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Integer getNatOperacao() {
		return natOperacao;
	}

	public void setNatOperacao(Integer natOperacao) {
		this.natOperacao = natOperacao;
	}

	public Double getCodPgto() {
		return codPgto;
	}

	public void setCodPgto(Double codPgto) {
		this.codPgto = codPgto;
	}
	
}
