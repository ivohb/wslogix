package com.wslogix.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;


@Embeddable
@Table(name = "item")
public class ClienteItemKey implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "cod_empresa", length = 2)
	private String empresa;

	@Column(name = "cod_cliente_matriz", length = 2)
	private String cliente;	
	
	@Column(name = "cod_item", length = 15)
	private String item;

	public ClienteItemKey() { }

	public ClienteItemKey(String empresa, String cliente, String item) {
		super();
		this.empresa = empresa;
		this.cliente = cliente;
		this.item = item;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

}
