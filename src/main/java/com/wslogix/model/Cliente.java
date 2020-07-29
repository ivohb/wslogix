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
@Table(name = "clientes")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cod_cliente", length = 15)
	private String id;
	
	@Column(name = "cod_class", length = 1)
	private String codClasse;
	
	@Column(name = "nom_cliente", length = 36)
	private String nome;

	@Column(name = "end_cliente", length = 36)
	private String endereco;

	@Column(name = "den_bairro", length = 19)
	private String bairro;

	@Column(name = "cod_cidade", length = 5)
	private String codCidade;

	@Column(name = "cod_cep", length = 9)
	private String cep;

	@Column(name = "num_caixa_postal", length = 5)
	private String caixaPostal;

	@Column(name = "num_telefone", length = 15)
	private String fone;

	@Column(name = "num_fax", length = 15)
	private String fax;

	@Column(name = "num_telex", length = 15)
	private String telex;

	@Column(name = "num_suframa") // length 9
	private Integer numSuframa;

	@Column(name = "cod_tip_cli", length = 2)
	private String codTipo;

	@Column(name = "den_marca", length = 12)
	private String marca;

	@Column(name = "nom_reduzido", length = 12)
	private String codinome;

	@Column(name = "den_frete_posto", length = 14)
	private String fretePosto;

	@Column(name = "num_cgc_cpf", length = 19)
	private String cnpj;

	@Column(name = "ins_estadual", length = 16)
	private String inscEstadual;

	@Column(name = "cod_portador") // length = 4
	private Integer codPortador;
			
	@Column(name = "ies_tip_portador", length = 1)
	private String tipPortador;
			
	@Column(name = "cod_cliente_matriz", length = 15)
	private String codMatriz;

	@Column(name = "cod_consig", length = 15)
	private String codConsig;
	        
	@Column(name = "ies_cli_forn", length = 1)
	private String clifornec;
			
	@Column(name = "ies_zona_franca", length = 1)
	private String zonaFranca;
			
	@Column(name = "ies_situacao", length = 1)
	private String situacao;
			
	@Column(name = "cod_rota") // length = 5
	private Integer codRota;
			
	@Column(name = "cod_praca") //length = 5
	private Integer codPraca;

	@Column(name = "dat_cadastro")
	@JsonFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date datCadastro;

	@Column(name = "dat_atualiz")
	@JsonFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date datAtualiz;
			
	@Column(name = "nom_contato", length = 20)
	private String contato;
			
	@Column(name = "dat_fundacao")
	@JsonFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date datFundacao;
			
	@Column(name = "cod_local") //length = 5
	private Integer codLocal;
			
    public Cliente() {}
    
    public Cliente(String id, String nome, String cnpj) {
    	this.id = id;
    	this.nome = nome;
    	this.cnpj = cnpj;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodClasse() {
		return codClasse;
	}

	public void setCodClasse(String codClasse) {
		this.codClasse = codClasse;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getCodCidade() {
		return codCidade;
	}

	public void setCodCidade(String codCidade) {
		this.codCidade = codCidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCaixaPostal() {
		return caixaPostal;
	}

	public void setCaixaPostal(String caixaPostal) {
		this.caixaPostal = caixaPostal;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getTelex() {
		return telex;
	}

	public void setTelex(String telex) {
		this.telex = telex;
	}

	public Integer getNumSuframa() {
		return numSuframa;
	}

	public void setNumSuframa(Integer numSuframa) {
		this.numSuframa = numSuframa;
	}

	public String getCodTipo() {
		return codTipo;
	}

	public void setCodTipo(String codTipo) {
		this.codTipo = codTipo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCodinome() {
		return codinome;
	}

	public void setCodinome(String codinome) {
		this.codinome = codinome;
	}

	public String getFretePosto() {
		return fretePosto;
	}

	public void setFretePosto(String fretePosto) {
		this.fretePosto = fretePosto;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscEstadual() {
		return inscEstadual;
	}

	public void setInscEstadual(String inscEstadual) {
		this.inscEstadual = inscEstadual;
	}

	public Integer getCodPortador() {
		return codPortador;
	}

	public void setCodPortador(Integer codPortador) {
		this.codPortador = codPortador;
	}

	public String getTipPortador() {
		return tipPortador;
	}

	public void setTipPortador(String tipPortador) {
		this.tipPortador = tipPortador;
	}

	public String getCodMatriz() {
		return codMatriz;
	}

	public void setCodMatriz(String codMatriz) {
		this.codMatriz = codMatriz;
	}

	public String getCodConsig() {
		return codConsig;
	}

	public void setCodConsig(String codConsig) {
		this.codConsig = codConsig;
	}

	public String getClifornec() {
		return clifornec;
	}

	public void setClifornec(String clifornec) {
		this.clifornec = clifornec;
	}

	public String getZonaFranca() {
		return zonaFranca;
	}

	public void setZonaFranca(String zonaFranca) {
		this.zonaFranca = zonaFranca;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Integer getCodRota() {
		return codRota;
	}

	public void setCodRota(Integer codRota) {
		this.codRota = codRota;
	}

	public Integer getCodPraca() {
		return codPraca;
	}

	public void setCodPraca(Integer codPraca) {
		this.codPraca = codPraca;
	}

	public Date getDatCadastro() {
		return datCadastro;
	}

	public void setDatCadastro(Date datCadastro) {
		this.datCadastro = datCadastro;
	}

	public Date getDatAtualiz() {
		return datAtualiz;
	}

	public void setDatAtualiz(Date datAtualiz) {
		this.datAtualiz = datAtualiz;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public Date getDatFundacao() {
		return datFundacao;
	}

	public void setDatFundacao(Date datFundacao) {
		this.datFundacao = datFundacao;
	}

	public Integer getCodLocal() {
		return codLocal;
	}

	public void setCodLocal(Integer codLocal) {
		this.codLocal = codLocal;
	}

}
