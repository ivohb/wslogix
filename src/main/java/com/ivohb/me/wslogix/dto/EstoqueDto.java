package com.ivohb.me.wslogix.dto;

import java.io.Serializable;

public class EstoqueDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String item;
	private String local;
	private Double saldo;
	
	public EstoqueDto() {}

	public EstoqueDto(String item, String local, Double saldo) {
		super();
		this.item = item;
		this.local = local;
		this.saldo = saldo;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	
}
