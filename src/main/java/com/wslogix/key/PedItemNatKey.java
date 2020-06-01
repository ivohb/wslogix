package com.wslogix.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Table(name = "ped_item_nat")
public class PedItemNatKey implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "cod_empresa", length = 2)
	private String empresa;
	@Column(name = "num_pedido")
	private Integer numero;
	@Column(name = "num_sequencia")
	private Integer sequencia;
	@Column(name = "ies_tipo",length = 1)
	private String tipo;
	
	public PedItemNatKey() { }

	public PedItemNatKey(String empresa, 
			Integer numero, Integer sequencia, String tipo) {
		super();
		this.empresa = empresa;
		this.numero = numero;
		this.sequencia = sequencia;
		this.tipo = tipo;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


}
