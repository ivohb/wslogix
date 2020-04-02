package com.ivohb.me.wslogix.validation;

public class ValidaSenha {

	public static boolean isValidSenha(final String senha) {
		if (senha.length() < 4 || senha.length() > 10) {
			return false;
		}
		String maiusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String minusculas = "abcdefghijklmnopqrstuvwxyz";
		String digitos = "0123456789";
		String especiais = "!@#$%&*()+?<>{[}]=-_|/.,;:";
		String digito = null;
		int qtdMaiusc = 0;
		int qtdMinusc = 0;
		int qtdDigit = 0;
		int qtdEspec = 0;
		
		for (int i = 0; i < senha.length(); i++) {
			
			digito = senha.substring(i, i + 1);
			
			if (maiusculas.contains(digito)) {
				qtdMaiusc++;
			} else {
				if (digitos.contains(digito)) {
					qtdDigit++;
				} else {
					if (especiais.contains(digito)) {
						qtdEspec++;
					} else {
						if (minusculas.contains(digito)) {
							qtdMinusc++;
						}
					}
				}
			}
		}
		
		if (qtdDigit == 0 || qtdEspec == 0 || qtdMaiusc == 0 || qtdMinusc == 0) {
			return false;
		}
		
		return true;
	}
}
