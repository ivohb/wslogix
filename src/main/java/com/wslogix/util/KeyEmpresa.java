package com.wslogix.util;

public class KeyEmpresa {

	public static void main(String[] args) {
		Biblioteca bib = new Biblioteca();
		String key = bib.chaveProduto("052.213.326/0001-09".substring(1)); //13157758152027468
		System.out.println(key);
		key = bib.chaveProduto("52.213.326/0001-09"); //13157758152027468
		System.out.println(key);
	}

}
