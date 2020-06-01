package com.wslogix.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wslogix.key.PedidoVdpKey;

@Entity
@Table(name = "pedidos")
public class PedidoVdp implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PedidoVdpKey id = new PedidoVdpKey();
	@Column(name = "cod_cliente", length = 15)
	private String cliente;
	@Column(name = "pct_comissao")
	private Double pctComissao;
	@Column(name = "num_pedido_repres", length = 10)
	private String pedRepres;
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "dat_emis_repres")
	private Date emisRepres;
	@Column(name = "cod_nat_oper")
	private Integer natOper;
	@Column(name = "cod_transpor", length = 15)
	private String transportador;
	@Column(name = "cod_consig", length = 15)
	private String consignatario;
	@Column(name = "ies_finalidade")
	private Double finalidade;
	@Column(name = "ies_frete")
	private Double tipFrete;
	@Column(name = "ies_preco", length = 1 )
	private String tipPreco;
	@Column(name = "cod_cnd_pgto")
	private Double codPgto;
	@Column(name = "pct_desc_financ")
	private Double pctDescFinanc;
	@Column(name = "ies_embal_padrao", length = 1 )
	private String embalPadrao;
	@Column(name = "ies_tip_entrega")
	private Double tipEntrega;
	@Column(name = "ies_aceite", length = 1 )
	private String aceite;
	@Column(name = "ies_sit_pedido", length = 1 )
	private String situacao;
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "dat_pedido")
	private Date emissao;
	@Column(name = "num_pedido_cli", length = 25)
	private String pedCliente;
	@Column(name = "pct_desc_adic")
	private Double pctDescAdic;
	@Column(name = "num_list_preco")
	private Double lista;
	@Column(name = "cod_repres")
	private Double representante;
	@Column(name = "cod_repres_adic")
	private Double represAdicional;
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "dat_alt_sit")
	private Date datAltSit;
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "dat_cancel")
	private Date datCancel;
	@Column(name = "cod_tip_venda")
	private Double tipVenda;
	@Column(name = "cod_motivo_can")
	private Double motCancel;
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "dat_ult_fatur")
	private Date datUltFat;
	@Column(name = "cod_moeda")
	private Double moeda;
	@Column(name = "ies_comissao", length = 1)
	private String comissao;
	@Column(name = "pct_frete")
	private Double pctFrete;
	@Column(name = "cod_tip_carteira", length = 2)
	private String carteira;
	@Column(name = "num_versao_lista")
	private Double versaoLista;
	@Column(name = "cod_local_estoq", length = 10)
	private String localEstoque;

	public PedidoVdp() {}
	
	public PedidoVdp(String empresa, Integer numero, String cliente) {
		super();
		id.setNumero(numero);
		id.setNumero(numero);
		this.cliente = cliente;		
	}
	
	public PedidoVdpKey getId() {
		return id;
	}

	public void setId(PedidoVdpKey id) {
		this.id = id;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Double getPctComissao() {
		return pctComissao;
	}

	public void setPctComissao(Double pctComissao) {
		this.pctComissao = pctComissao;
	}

	public String getPedRepres() {
		return pedRepres;
	}

	public void setPedRepres(String pedRepres) {
		this.pedRepres = pedRepres;
	}

	public Date getEmisRepres() {
		return emisRepres;
	}

	public void setEmisRepres(Date emisRepres) {
		this.emisRepres = emisRepres;
	}

	public Integer getNatOper() {
		return natOper;
	}

	public void setNatOper(Integer natOper) {
		this.natOper = natOper;
	}

	public String getTransportador() {
		return transportador;
	}

	public void setTransportador(String transportador) {
		this.transportador = transportador;
	}

	public String getConsignatario() {
		return consignatario;
	}

	public void setConsignatario(String consignatario) {
		this.consignatario = consignatario;
	}

	public Double getFinalidade() {
		return finalidade;
	}

	public void setFinalidade(Double finalidade) {
		this.finalidade = finalidade;
	}

	public Double getTipFrete() {
		return tipFrete;
	}

	public void setTipFrete(Double tipFrete) {
		this.tipFrete = tipFrete;
	}

	public String getTipPreco() {
		return tipPreco;
	}

	public void setTipPreco(String tipPreco) {
		this.tipPreco = tipPreco;
	}

	public Double getCodPgto() {
		return codPgto;
	}

	public void setCodPgto(Double codPgto) {
		this.codPgto = codPgto;
	}

	public Double getPctDescFinanc() {
		return pctDescFinanc;
	}

	public void setPctDescFinanc(Double pctDescFinanc) {
		this.pctDescFinanc = pctDescFinanc;
	}

	public String getEmbalPadrao() {
		return embalPadrao;
	}

	public void setEmbalPadrao(String embalPadrao) {
		this.embalPadrao = embalPadrao;
	}

	public Double getTipEntrega() {
		return tipEntrega;
	}

	public void setTipEntrega(Double tipEntrega) {
		this.tipEntrega = tipEntrega;
	}

	public String getAceite() {
		return aceite;
	}

	public void setAceite(String aceite) {
		this.aceite = aceite;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Date getEmissao() {
		return emissao;
	}

	public void setEmissao(Date emissao) {
		this.emissao = emissao;
	}

	public String getPedCliente() {
		return pedCliente;
	}

	public void setPedCliente(String pedCliente) {
		this.pedCliente = pedCliente;
	}

	public Double getPctDescAdic() {
		return pctDescAdic;
	}

	public void setPctDescAdic(Double pctDescAdic) {
		this.pctDescAdic = pctDescAdic;
	}

	public Double getLista() {
		return lista;
	}

	public void setLista(Double lista) {
		this.lista = lista;
	}

	public Double getRepresentante() {
		return representante;
	}

	public void setRepresentante(Double representante) {
		this.representante = representante;
	}

	public Double getRepresAdicional() {
		return represAdicional;
	}

	public void setRepresAdicional(Double represAdicional) {
		this.represAdicional = represAdicional;
	}

	public Date getDatAltSit() {
		return datAltSit;
	}

	public void setDatAltSit(Date datAltSit) {
		this.datAltSit = datAltSit;
	}

	public Date getDatCancel() {
		return datCancel;
	}

	public void setDatCancel(Date datCancel) {
		this.datCancel = datCancel;
	}

	public Double getTipVenda() {
		return tipVenda;
	}

	public void setTipVenda(Double tipVenda) {
		this.tipVenda = tipVenda;
	}

	public Double getMotCancel() {
		return motCancel;
	}

	public void setMotCancel(Double motCancel) {
		this.motCancel = motCancel;
	}

	public Date getDatUltFat() {
		return datUltFat;
	}

	public void setDatUltFat(Date datUltFat) {
		this.datUltFat = datUltFat;
	}

	public Double getMoeda() {
		return moeda;
	}

	public void setMoeda(Double moeda) {
		this.moeda = moeda;
	}

	public String getComissao() {
		return comissao;
	}

	public void setComissao(String comissao) {
		this.comissao = comissao;
	}

	public Double getPctFrete() {
		return pctFrete;
	}

	public void setPctFrete(Double pctFrete) {
		this.pctFrete = pctFrete;
	}

	public String getCarteira() {
		return carteira;
	}

	public void setCarteira(String carteira) {
		this.carteira = carteira;
	}

	public Double getVersaoLista() {
		return versaoLista;
	}

	public void setVersaoLista(Double versaoLista) {
		this.versaoLista = versaoLista;
	}

	public String getLocalEstoque() {
		return localEstoque;
	}

	public void setLocalEstoque(String localEstoque) {
		this.localEstoque = localEstoque;
	}
		
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		StringBuilder builder = new StringBuilder();
		builder.append("Empresa: ");
		builder.append(id.getEmpresa());
		builder.append(", Número: ");
		builder.append(id.getNumero());
		builder.append(", Emissão: ");
		builder.append(sdf.format(getEmissao()));
		builder.append(", Cliente: ");
		builder.append(getCliente());
		builder.append(", Situação do pagamento: ");
		return builder.toString();
	}
}
