package com.wslogix.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wslogix.key.PedItemKey;

@Entity
@Table(name = "ped_itens")
public class PedidoItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PedItemKey id = new PedItemKey();
	@Column(name = "cod_item", length = 15)
	private String item;
	@Column(name = "pct_desc_adic")
	private Double pctDescAdic;
	@Column(name = "pre_unit")
	private Double preUnit;
	@Column(name = "qtd_pecas_solic")
	private Double qtdSolicit;
	@Column(name = "qtd_pecas_atend")
	private Double qtdAtendida;
	@Column(name = "qtd_pecas_cancel")
	private Double qtdCancelada;
	@Column(name = "qtd_pecas_reserv")
	private Double qtdReservada;
	@Column(name = "prz_entrega")
	private Date przEntrega;
	@Column(name = "val_desc_com_unit")
	private Double descComUnit;
	@Column(name = "val_frete_unit")
	private Double valFreteUnit;
	@Column(name = "val_seguro_unit")
	private Double valSeguroUnit;
	@Column(name = "qtd_pecas_romaneio")
	private Double qtdRomaneio;
	@Column(name = "pct_desc_bruto")
	private Double pctDescBruto;
	
	public PedidoItem() {}
	
	public PedidoItem(String empresa, Integer numero, 
			Integer sequencia, String item) {
		super();
		id.setNumero(numero);
		id.setNumero(numero);
		id.setSequencia(sequencia); 	
		this.item = item;
	}

	public double getSubTotal() {
		return (qtdSolicit - qtdCancelada) * preUnit;
	}

	public PedItemKey getId() {
		return id;
	}

	public void setId(PedItemKey id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Double getPctDescAdic() {
		return pctDescAdic;
	}

	public void setPctDescAdic(Double pctDescAdic) {
		this.pctDescAdic = pctDescAdic;
	}

	public Double getPreUnit() {
		return preUnit;
	}

	public void setPreUnit(Double preUnit) {
		this.preUnit = preUnit;
	}

	public Double getQtdSolicit() {
		return qtdSolicit;
	}

	public void setQtdSolicit(Double qtdSolicit) {
		this.qtdSolicit = qtdSolicit;
	}

	public Double getQtdAtendida() {
		return qtdAtendida;
	}

	public void setQtdAtendida(Double qtdAtendida) {
		this.qtdAtendida = qtdAtendida;
	}

	public Double getQtdCancelada() {
		return qtdCancelada;
	}

	public void setQtdCancelada(Double qtdCancelada) {
		this.qtdCancelada = qtdCancelada;
	}

	public Double getQtdReservada() {
		return qtdReservada;
	}

	public void setQtdReservada(Double qtdReservada) {
		this.qtdReservada = qtdReservada;
	}

	public Date getPrzEntrega() {
		return przEntrega;
	}

	public void setPrzEntrega(Date przEntrega) {
		this.przEntrega = przEntrega;
	}

	public Double getDescComUnit() {
		return descComUnit;
	}

	public void setDescComUnit(Double descComUnit) {
		this.descComUnit = descComUnit;
	}

	public Double getValFreteUnit() {
		return valFreteUnit;
	}

	public void setValFreteUnit(Double valFreteUnit) {
		this.valFreteUnit = valFreteUnit;
	}

	public Double getValSeguroUnit() {
		return valSeguroUnit;
	}

	public void setValSeguroUnit(Double valSeguroUnit) {
		this.valSeguroUnit = valSeguroUnit;
	}

	public Double getQtdRomaneio() {
		return qtdRomaneio;
	}

	public void setQtdRomaneio(Double qtdRomaneio) {
		this.qtdRomaneio = qtdRomaneio;
	}

	public Double getPctDescBruto() {
		return pctDescBruto;
	}

	public void setPctDescBruto(Double pctDescBruto) {
		this.pctDescBruto = pctDescBruto;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		builder.append(getItem());
		builder.append(", Qte: ");
		builder.append(getQtdSolicit());
		builder.append(", Preço unitário: ");
		builder.append(nf.format(getPreUnit()));
		builder.append(", Subtotal: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}
}
