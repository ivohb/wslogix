package com.wslogix.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cod_empresa", length = 2)
	private String id;

	@Column(name = "den_empresa", length = 36)
	private String nome;

	@Column(name = "den_reduz", length = 10)
	private String codinome;

	@Column(name = "end_empresa", length = 36)
	private String endereco;

	@Column(name = "den_bairro", length = 19)
	private String bairro;

	@Column(name = "den_munic", length = 30)
	private String municipio;

	@Column(name = "uni_feder", length = 2)
	private String uf;

	@Column(name = "ins_estadual", length = 16)
	private String incricao;

	@Column(name = "num_cgc", length = 19)
	private String cnpj;

	@Column(name = "num_caixa_postal", length = 5)
	private String caixaPostal;

	@Column(name = "cod_cep", length = 9)
	private String cep;

	@Column(name = "num_telefone", length = 15)
	private String fone;

	@Column(name = "num_telex", length = 15)
	private String telex;

	@Column(name = "num_fax", length = 15)
	private String fax;

	@Column(name = "end_telegraf", length = 36)
	private String chave;

	@Column(name = "num_reg_junta", length = 15)
	private String regJunta;

	@Column(name = "dat_inclu_junta", length = 10)
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date datIncJunta;

	@Column(name = "ies_filial", length = 1)
	private String ehFilial;

	@Column(name = "dat_fundacao")
	@JsonFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date datFundacao;

	@Column(name = "cod_cliente", length = 15)
	private String codCliente;

	public Empresa() {}
	
	public Empresa(String id, String codinome, String nome, String cnpj ) {
		this.id = id;
		this.codinome = codinome;
		this.nome = nome;
		this.cnpj = cnpj;
	}	

	public Empresa(String id, String codinome, String nome, String cnpj, String chave) {
		this.id = id;
		this.nome = nome;
		this.codinome = codinome;
		this.cnpj = cnpj;
		this.chave = chave;
	}	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getIncricao() {
		return incricao;
	}

	public void setIncricao(String incricao) {
		this.incricao = incricao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCaixaPostal() {
		return caixaPostal;
	}

	public void setCaixaPostal(String caixaPostal) {
		this.caixaPostal = caixaPostal;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getTelex() {
		return telex;
	}

	public void setTelex(String telex) {
		this.telex = telex;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getRegJunta() {
		return regJunta;
	}

	public void setRegJunta(String regJunta) {
		this.regJunta = regJunta;
	}

	public Date getDatIncJunta() {
		return datIncJunta;
	}

	public void setDatIncJunta(Date datIncJunta) {
		this.datIncJunta = datIncJunta;
	}

	public String getEhFilial() {
		return ehFilial;
	}

	public void setEhFilial(String ehFilial) {
		this.ehFilial = ehFilial;
	}

	public Date getDatFundacao() {
		return datFundacao;
	}

	public void setDatFundacao(Date datFundacao) {
		this.datFundacao = datFundacao;
	}

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	
}
