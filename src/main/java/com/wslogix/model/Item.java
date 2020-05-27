package com.wslogix.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemPrimaryKey id = new ItemPrimaryKey();
		
	@Column(name = "den_item", length = 76)
	private String nome;

	@Column(name = "den_item_reduz", length = 18)
	private String codinome;

	@Column(name = "cod_local_estoq", length = 10)
	private String localEstoq;
	
	public Item() {}

	public Item(String empresa, String codigo, String nome) {
		super();
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodinome() {
		return codinome;
	}

	public void setCodinome(String codinome) {
		this.codinome = codinome;
	}

	public String getLocalEstoq() {
		return localEstoq;
	}

	public void setLocalEstoq(String localEstoq) {
		this.localEstoq = localEstoq;
	}		
}
