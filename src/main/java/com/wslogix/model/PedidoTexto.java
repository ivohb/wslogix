package com.wslogix.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wslogix.key.PedItemKey;

@Entity
@Table(name = "ped_itens_texto")
public class PedidoTexto implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PedItemKey id = new PedItemKey();
	@Column(name = "den_texto_1", length = 76)
	private String textoUm;
	@Column(name = "den_texto_2", length = 76)
	private String textoDois;
	@Column(name = "den_texto_3", length = 76)
	private String textoTres;
	@Column(name = "den_texto_4", length = 76)
	private String textoQuatro;
	@Column(name = "den_texto_5", length = 76)
	private String textoCinco;
	
	public PedidoTexto() {}

	public PedItemKey getId() {
		return id;
	}

	public void setId(PedItemKey id) {
		this.id = id;
	}

	public String getTextoUm() {
		return textoUm;
	}

	public void setTextoUm(String textoUm) {
		this.textoUm = textoUm;
	}

	public String getTextoDois() {
		return textoDois;
	}

	public void setTextoDois(String textoDois) {
		this.textoDois = textoDois;
	}

	public String getTextoTres() {
		return textoTres;
	}

	public void setTextoTres(String textoTres) {
		this.textoTres = textoTres;
	}

	public String getTextoQuatro() {
		return textoQuatro;
	}

	public void setTextoQuatro(String textoQuatro) {
		this.textoQuatro = textoQuatro;
	}

	public String getTextoCinco() {
		return textoCinco;
	}

	public void setTextoCinco(String textoCinco) {
		this.textoCinco = textoCinco;
	}
	

}
