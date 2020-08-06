package com.wslogix.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wslogix.key.PedItemKey;

@Entity
@Table(name = "ped_end_ent")
public class PedidoEntrega implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PedItemKey id = new PedItemKey();
	@Column(name = "end_entrega", length = 36)
	private String endereco;
	@Column(name = "den_bairro", length = 19)
	private String bairro;
	@Column(name = "cod_cidade", length = 5)
	private String cidade;
	@Column(name = "cod_cep", length = 9)
	private String cep;
	@Column(name = "num_cgc", length = 19)
	private String cnpj;
	@Column(name = "ins_estadual", length = 19)
	private String incricao;
	
	public PedidoEntrega() {}
	
	public PedItemKey getId() {
		return id;
	}
	public void setId(PedItemKey id) {
		this.id = id;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getIncricao() {
		return incricao;
	}
	public void setIncricao(String incricao) {
		this.incricao = incricao;
	}
	
}
