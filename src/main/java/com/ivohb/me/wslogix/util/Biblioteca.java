package com.ivohb.me.wslogix.util;


import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Biblioteca implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Verifica se a string recebida é um CPF
	 * 
	 * @param.: uma string
	 * @return: true, se for um cpf ou false, caso contrário
	 */
	public boolean isCpf(String cpf) {

		cpf = cpf.replace(" ", "");
		cpf = tiraFormato(cpf);

		if (cpf.length() == 11) {
			if (isNumerico(cpf))
				if (validaCpf(cpf))
					return true;
		}
		return false;
	}

	/**
	 * Remove do argumento campoFormatado os caracteres . - / _ 
	 * e retorno uma string sem esses caracteres especiais
	 */

	public String tiraFormato(String campoFormatado) {
		String dig, semFormato = "", formato = ":.-/_";

		for (int i = 0; i < campoFormatado.length(); i++) {
			dig = campoFormatado.substring(i, i + 1);
			if (formato.indexOf(dig) == -1)
				semFormato = semFormato + dig;
		}
		return semFormato;

	}

	/**
	 * Remove do argumento campoFormatado os caracteres . - / _ 
	 * e retorno uma string sem esses caracteres especiais
	 */

	public String formataCnpjCpf(String campo, String valor) {
		String formatado = "";

		if (campo.equalsIgnoreCase("cnpj")) {
			formatado = 
				valor.substring(0, 2)+"."+
				valor.substring(2, 5)+"."+
				valor.substring(5, 8)+"/"+
				valor.substring(8, 12)+"-"+
				valor.substring(12, 14);
		} else {
			formatado = 
				valor.substring(0, 3)+"."+
				valor.substring(3, 6)+"."+
				valor.substring(6, 9)+"-"+
				valor.substring(9, 11);
		}

		return formatado;

	}

	/**
	 * Função de auxílio à validação do cpf Calcula o dígito do cpf enviado e
	 * retorna true se for igual ao dois últimos caracteres do cpf, ou false,
	 * caso contrário
	 */
	private boolean validaCpf(String cpf) {
		String digCalculado, digEnviado;
		int d1, d2, digCpf, resto;

		d1 = d2 = 0;

		for (int j = 1; j < cpf.length() - 1; j++) {
			digCpf = Integer.valueOf(cpf.substring(j - 1, j)).intValue();
			d1 = d1 + (11 - j) * digCpf;
			d2 = d2 + (12 - j) * digCpf;
		}
		resto = (d1 % 11);
		if (resto < 2)
			d1 = 0;
		else
			d1 = 11 - resto;

		d2 += 2 * d1;
		resto = (d2 % 11);

		if (resto < 2)
			d2 = 0;
		else
			d2 = 11 - resto;

		digEnviado = cpf.substring(cpf.length() - 2, cpf.length());
		digCalculado = String.valueOf(d1) + String.valueOf(d2);

		return digEnviado.equals(digCalculado);
	}

	/**
	 * Verifica se o argumento é um int
	 * 
	 * @param.: uma string
	 * @return: true, se o argumento for int e false, caso contrário
	 */
	public boolean isNumerico(String valor) {
		
		String valido = "0123456789";

		for (int i = 0; i < valor.length(); i++) {
			if (valido.indexOf(valor.substring(i, i + 1)) == -1)
				return false;
		}
		return true;
	}

	/**
	 * Verifica se o endereço enviado contém virgula
	 * 
	 * @param.: uma string
	 * @return: true, se o argumento for válido e false, caso contrário
	 */
	public boolean temVirgula(String endereco) {
		
		String digito;

		for (int i = 0; i < endereco.length(); i++) {
			digito = endereco.substring(i, i + 1);
			if (digito.equalsIgnoreCase(","))
				return true;
		}
		return false;
	}

	public String getTexto(String codigo) throws Exception {

		String retorno = "";
		int numero;
		String str = null;
		
		for (int i = 0; i < codigo.length(); i+=3) {
			numero = Integer.parseInt(codigo.substring(i, i+3));
            str = Character.toString((char)numero);
            retorno += str;
		}
			
        return retorno;
	}
	
	/**
	 * @param.: uma string
	 * @return: uma string contendo o código ascii da string enviada como
	 *          argumento, ou vazio, se o argumento for inválido
	 */
	public String getAscii(String texto) throws Exception {
		texto = texto.trim();
		String txtAsc = "";
		String caracAsc;

		if (texto == null || texto.isEmpty()) {
			return txtAsc;
		}

		try { // armazena no vetor bytes o código ascii
			byte[] bytes = texto.getBytes("ASCII");
			for (int i = 0; i < bytes.length; i++) {
				caracAsc = ""+bytes[i];
				if (caracAsc.length() < 3) {
					caracAsc = strZero(Integer.parseInt(caracAsc), 3);
				}
				txtAsc += caracAsc; // concatena o código ascii de cada dígito
			}
			return txtAsc;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * @param.: uma string
	 * @return: um inteiro que é obtido a partir do código ascci da string
	 *          enviada como argumento, ou zero, se o argumento for inválido
	 */

	public int getControle(String texto) throws Exception {

		texto = texto.trim();
		int controle = 0;

		if (texto == null || texto.isEmpty()) {
			return controle;
		}

		try { // armazena no vetor bytes o código ascii
			byte[] bytes = texto.getBytes("ASCII");
			// ascii do 1o. dig * 1 + ascii do 2o. dig * 2 + ...
			for (int j = 0; j < bytes.length; j++) {
				controle += bytes[j] * (j + 1);
			}
		} catch (Exception e) {
		}

		return controle;
	}

	/**
	 * @param.: uma string
	 * @return: true, se a string contiver um int, e false, caso contrário
	 */
	@SuppressWarnings("unused")
	public boolean isInt(String argumento) {
		try {
			int ehInt = Integer.parseInt(argumento);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @param.: uma string
	 * @return: true, se a string contiver um double, e false, caso contrário
	 */
	@SuppressWarnings("unused")
	public boolean isDouble(String argumento) {
		try {
			double ehDouble = Double.parseDouble(argumento);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Verifica se a string recebida é um CNPJ
	 * @param.: uma string
	 * @return: true, se for um CNPJ ou false, caso contrário
	 */
	public boolean isCnpj(String cnpj) {

		cnpj = cnpj.replace(" ", "");
		cnpj = tiraFormato(cnpj);

		if (cnpj.length() == 14) {
			if (isNumerico(cnpj))
				if (validaCnpj(cnpj))
					return true;
		}
		return false;
	}

	/**
	 * @param: um número de cnpj do tipo string
	 * @return: true, se o parâmetro for um cnpj ou false, caso contrário)
	 */

	private boolean validaCnpj(String cnpj) {

		int soma = 0;
		int i, j, dig1, dig2;

		//obtém soma dos 4 primeiros dígitos multiplicados respectivamente
		//por 5, 4, 3 e 2
		
		j = 5;
		for (i = 0; i <= 3; i++) {
			soma = soma + Integer.parseInt(cnpj.charAt(i) + "") * j;
			j--;
		}

		//obtém soma dos 8 próximos dígitos multiplicados respectivamente
		//por 9, 8, 7, 6, 5, 4, 3 e 2

		j = 9;
		for (i = 4; i <= 11; i++) {
			soma = soma + Integer.parseInt(cnpj.charAt(i) + "") * j;
			j--;
		}

		//cálculo do primeiro dígito: se resto de soma/11 menor que 2, dig1=0.
		//Caso contrário, dig1 = 11 - resto
		
		dig1 = (soma % 11);
		
		if (dig1 < 2)
			dig1 = 0;
		else
			dig1 = 11 - dig1;
		
		if(dig1 != Integer.parseInt(cnpj.charAt(12) + ""))
			return false;
			
		//Segunda parte: obter o segundo dígito.

		//obtém soma dos 5 primeiros dígitos multiplicados respectivamente
		//por 6, 5, 4, 3 e 2
		
		j = 6;
		soma = 0;
		
		for (i = 0; i <= 4; i++) {
			soma = soma + Integer.parseInt(cnpj.charAt(i) + "") * j;
			j--;
		}

		//obtém soma dos 8 próximos dígitos multiplicados respectivamente
		//por 9, 8, 7, 6, 5, 4, 3 e 2

		j = 9;
		for (i = 5; i <= 12; i++) {
			soma = soma + Integer.parseInt(cnpj.charAt(i) + "") * j;
			j--;
		}

		//cálculo do segundo dígito: se resto de soma/11 menor que 2, dig1=0.
		//Caso contrário, dig1 = 11 - resto
		
		dig2 = (soma % 11);
		
		if (dig2 < 2)
			dig2 = 0;
		else
			dig2 = 11 - dig2;
		
		if(dig2 != Integer.parseInt(cnpj.charAt(13) + ""))
			return false;

		return true;
	}	

	/**
	 * @param:código do locale e uma data string no formato (yyyyMMdd) 
	 * @return: uma data string no formato MM/dd/yyyy ou dd/MM/yyyy 
	 */
	public String formataData(String locale, String data) {
		
		if  (data == null || data.isEmpty() || data.length() > 8)
			return data;
		if (locale.equalsIgnoreCase("en-US")) {
			data = data.substring(4, 6)+'/'+
					data.substring(6, 8)+'/'+
					data.substring(0, 4);
			
		} else {
			data = data.substring(6, 8)+'/'+
				    data.substring(4, 6)+'/'+ 
				    data.substring(0, 4);
		}
		
		return data;
	}

	/**
	 * @param: uma data string no formato (dd/MM/yyyy) 
	 * @return: uma data string no formato yyyy/MM/dd
	 */
	public String inverteData(String dt) {
		
		if  (dt.isEmpty() || dt.length() < 10)
			return dt;

		String data = tiraFormato(dt);		
		
		data = data.substring(0, 4)+'/'+
			       data.substring(4, 6)+'/'+ 
			       data.substring(6, 8);

		return data;
	}

	/**
	 * @param: uma data string no formato string
	 * @return: uma data date no formato enviado como parâmetro, ou null
	 *          se os parâmetros enviados não forem válidos
	 */
	public Date strToDate(String data) {

		try {
			DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
			Date date = (java.util.Date) df.parse(data);
			return date;			
		} catch (Exception e) {
			return null;			
		}
	}

	/**
	 * @param: uma data string ansi e o formato desejado no retorno
	 * @return: uma data date no formato enviado como parâmetro, ou null
	 *          se os parâmetros enviados não forem válidos
	 */
	public Date ansiToDate(String data, String formato) {
        
		data = dataExibicao(data, formato);
		
		if (!isData(data, formato))
			return null;

		try {
			DateFormat df = new SimpleDateFormat(formato);
			Date date = (java.util.Date) df.parse(data);
			return date;			
		} catch (Exception e) {
			return null;			
		}
	}

	/**
	 * @param: uma data string e o formato desejado no retorno
	 * @return: uma data date no formato enviado como parâmetro, ou null
	 *          se os parâmetros enviados não forem válidos
	 */
	public Date strToDate(String data, String formato) {

		if (!isData(data, formato))
			return null;

		try {
			DateFormat df = new SimpleDateFormat(formato);
			Date date = (java.util.Date) df.parse(data);
			return date;			
		} catch (Exception e) {
			return null;			
		}
	}

	/**
	 * @param: uma data e o formato desejado no retorno
	 * @return: uma data string no formato enviado como parâmetro, ou null
	 *          se os parâmetros enviados não forem válidos
	 */
	public String dateToStr(Date data, String formato) {

		try {
			DateFormat df = new SimpleDateFormat(formato);
			String datStr = df.format(data);
			return datStr;			
		} catch (Exception e) {
			return null;			
		}
	}

	/**
	 * @param: uma data string no formato (yyyy/MM/dd) e o formato do retorno
	 * @return: uma data string no formato enviado
	 */
	public String dataExibicao(String data, String formato) {
		
		if  (data.isEmpty() || formato.isEmpty())
			return data;
		
		String ano = data.substring(0, 4), 
		       mes = data.substring(5, 7),
		       dia = data.substring(8, 10);
		
		if (!isNumerico(ano))
			return data;
		
		if (formato.substring(0, 2).equals("dd")) 
			return dia+"/"+mes+"/"+ano;
		else
			return mes+"/"+dia+"/"+ano;
	}

	/**
	 * @param: uma data e o formato que a mesma se encontra, ou sejam:
	 *         dd/MM/yyyy ou MM/dd/yyyy 
	 * @return: uma data string no formato ansi (yyyy-MM-dd), ou a mesma
	 *          se ocorrer algum erro na conversão
	 */
	public String dataAnsi(String data, String formato) {

		//Calendar dat = Calendar.getInstance();
		java.util.Date dat = new java.util.Date();

		try {
			DateFormat df = new SimpleDateFormat(formato);
			//Date date = (java.util.Date) df.parse(data);
			df = new SimpleDateFormat("yyyy-MM-dd");
			data = df.format(dat);
			return data;			
		} catch (Exception e) {
			return data;			
		}
	}

	/**
	 * @param: uma data e o formato que a mesma se encontra, ou sejam:
	 *         dd/MM/yyyy ou MM/dd/yyyy 
	 * @return: uma data string no formato ansi (yyyy-MM-dd), ou a mesma
	 *          se ocorrer algum erro na conversão
	 */
	public String dataAnsi() {
		
		String data = null;
		//Calendar dat = Calendar.getInstance();
		java.util.Date date = new java.util.Date();

		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			data = df.format(date);
			return data;			
		} catch (Exception e) {
			return data;			
		}
	}

	/**
	 * @param: uma data no formato do sistema
	 * @return: uma string no formato ANSI (ano-mes-dia)
	 */
	public String dataAnsi(Date data) {

		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String dataAnsi = df.format(data);
			return dataAnsi;			
		} catch (Exception e) {
			return null;			
		}

	}

	/**
	 * @param: o formato desejado
	 * @return: uma  data string no formato recebido)
	 */
	public Date mudaFormato(Date data, String formato) {

		try {
			
			DateFormat df = new SimpleDateFormat(formato);
			String dat = df.format(data);
			data = (java.util.Date) df.parse(dat);
			return data;
		} catch (Exception e) {
			return data;			
		}
	}
	/**
	 * @return: uma data string no formato ANSI, n anos antes ou após a data atual
	 */
	
	public String dataPesquisa(int n) {
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, n);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(c.getTime());
	}

	/**
	 * @return: uma data string no formato ANSI, n anos antes ou após a data atual
	 */
	
	public String dataPesquisa(int n, String formato) {
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, n);
		DateFormat df = new SimpleDateFormat(formato);
		return df.format(c.getTime());
	}

	/**
	 * @return: uma data string no formato ANSI 3 anos antes da data atual
	 */
	
	public String dataIniPesquisa() {
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, -3);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(c.getTime());
	}

	/**
	 * @return: uma data string no formato ANSI 3 anos após a data atual
	 */
	
	public String dataFimPesquisa() {
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, 3);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(c.getTime());
	}

	/**
	 * param: formato desejado
	 * @return: a data atual no formato enviado
	 */
	
	public String dataAtual(String formato) {
		
		Calendar c = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat(formato);
		return df.format(c.getTime());
	}

	/**
	 * param: formato desejado (hh / hh:mm / hh:mm:ss)
	 * @return: a hora atual no formato enviado
	 */
	
	public String horaAtual(String formato) {
		
		String hh, mm, ss;
		
		Calendar c = Calendar.getInstance();
		
		hh = strZero(c.get(Calendar.HOUR_OF_DAY),2);
		mm = strZero(c.get(Calendar.MINUTE),2);
		ss = strZero(c.get(Calendar.SECOND),2);
		
		if (formato.equalsIgnoreCase("hh"))
			return(hh);
		else
			if (formato.equalsIgnoreCase("hh:mm"))
				return(hh+":"+mm);
			else
				return(hh+":"+mm+":"+ss);		
	}

	public String strZero(int valor, int tamanho) {
		
		String retorno = ""+valor;
		
		int qtdZeros = tamanho - retorno.length();
		retorno = "";
		
		for (int i = 1; i <= qtdZeros; i++) {
			retorno+="0";
		}
		
		retorno+=valor;
		
		return retorno;
		
	}

	public String strZero(long valor, int tamanho) {
		
		String retorno = ""+valor;
		
		int qtdZeros = tamanho - retorno.length();
		retorno = "";
		
		for (int i = 1; i <= qtdZeros; i++) {
			retorno+="0";
		}
		
		retorno+=valor;
		
		return retorno;
		
	}
    public String chaveProduto(String cnpj) {

    	long chave = 0;
    	int digCnpj;
    	cnpj = cnpj.trim();
    	cnpj = tiraFormato(cnpj);
		
		for (int j = 1; j <= cnpj.length(); j++) {
			digCnpj = Integer.valueOf(cnpj.substring(j - 1, j)).intValue();
			chave = chave + (digCnpj * j);
		}

		chave = chave * Long.parseLong(cnpj);
		
		return ""+chave;
    }
    
	public boolean isData(String data, String formato) {

		data = data.replace(" ", "");

		if (data.length() != formato.length())
			return false;

		int dia, mes, ano, maxDia;
		mes = ano = 0;

		try {
			if (formato.substring(0, 2).equals("dd")) {
				dia = Integer.parseInt(data.substring(0, 2));
				mes = Integer.parseInt(data.substring(3, 5));
				ano = Integer.parseInt(data.substring(6, 10));
			} else {
				if (formato.substring(0, 2).equals("MM")) {
					mes = Integer.parseInt(data.substring(0, 2));
					dia = Integer.parseInt(data.substring(3, 5));
					ano = Integer.parseInt(data.substring(6, 10));
				} else {
					ano = Integer.parseInt(data.substring(0, 4));
					mes = Integer.parseInt(data.substring(5, 7));
					dia = Integer.parseInt(data.substring(8, 10));
				}
			}			
		} catch (Exception e) {
			return false;
		}

		if (mes < 1 || mes > 12)
			return false;

		if (mes == 4 || mes == 6 || mes == 8 || mes == 11) {
			maxDia = 30;
		} else if (mes == 2) {
			if (ano % 4 == 0)
				maxDia = 29;
			else
				maxDia = 28;
		} else
			maxDia = 31;

		if (dia > 0 && dia <= maxDia)
			return true;
		else
			return false;

	}

	public String setDataAbreviada(String data) {
		
		data = data.replace(" ", "");
		
		if (data.length() != 6)
			return data;
		
		String dia = data.substring(0, 2), 
	           mes = data.substring(2, 4),
	           ano = data.substring(4, 6);

		int anoInt = Integer.parseInt(ano) + 2000;
		ano = ""+anoInt;
		
		return dia+"/"+mes+"/"+ano;
		
	}
	
	
	public String setHoraMinuto(String hora) {
		
		String horaRetorno;
		
		hora = hora.replace(" ", "");
		horaRetorno = hora;
		
		if (hora.length() >= 3 || hora.length() == 0)
			return hora;
		
		if (hora.length() == 2)
			horaRetorno = hora.trim()+":00";
		else
			horaRetorno = "0"+hora.trim()+":00";
		
		return horaRetorno;
	}
	
	/**
	 * param: uma hora do tipo string
	 * @return: true, se a hora estiver no formato hh:mm entre 00:00 e 23:59
	 *       ou false, caso contrário. 
	 */

	public boolean isHoraMinuto(String hora) {
		
		int hh, mm; String doisPontos;

		hora = hora.replace(" ", "");

		if (hora.length() != 5)
			return false;

		doisPontos = hora.substring(2,3);
		
		if (!doisPontos.equals(":"))
			return false;

		hora = tiraFormato(hora);

		if(!isNumerico(hora))
			return false;
		
		hh = Integer.parseInt(hora.substring(0, 2));
	    mm = Integer.parseInt(hora.substring(2, 4));

	    if (hh > 23 || mm > 59)
	    	return false;
			
		return true;
	}
	
	/**
	 * param: um double e as casas decimais desejadas para o double
	 * @return: um double truncado com a quantidade de casas
	 *          decimais determinada pelo segundo parâmetro
	 */

	public double truncaDouble(double valor, int qtdCasasDecimais) {

		String valorEnviado, parteInteira, parteDecimal;
		int posicaoPonto = 0;
		
		valorEnviado = ""+valor;
		
		for (int i = 0; i < valorEnviado.length(); i++) {
			char c = valorEnviado.charAt(i);
			if (c == '.') {
				posicaoPonto = i;
				break;
			}
		}
		
		if (posicaoPonto == 0)
			return valor;
		
		parteInteira = valorEnviado.substring(0,posicaoPonto);
		parteDecimal = valorEnviado.substring(posicaoPonto+1,valorEnviado.length());
		
		if (parteDecimal.length() > qtdCasasDecimais)
			parteDecimal = parteDecimal.substring(0,qtdCasasDecimais);
		
		return Double.parseDouble(parteInteira+"."+parteDecimal);
	}

	/**
	 * param: um float e as casas decimais desejadas para o double
	 * @return: um float truncado com a quantidade de casas
	 *          decimais determinada pelo segundo parâmetro
	 */

	public float arredondaFloat(float valor) {

		String valorEnviado, parteInteira, parteDecimal, sArredonda;
		int posicaoPonto = 0;
		int qtdCasasDecimais = 2;
		int iArredonda = 0;
		
		valorEnviado = ""+valor;
		
		for (int i = 0; i < valorEnviado.length(); i++) {
			char c = valorEnviado.charAt(i);
			if (c == '.') {
				posicaoPonto = i;
				break;
			}
		}
		
		if (posicaoPonto == 0) {
			valorEnviado = valorEnviado+".00";
			return  Float.parseFloat(valorEnviado);
		}

		if (posicaoPonto == valorEnviado.length()) {
			valorEnviado = valorEnviado+"00";
			return  Float.parseFloat(valorEnviado);
		}

		parteInteira = valorEnviado.substring(0,posicaoPonto);
		parteDecimal = valorEnviado.substring(posicaoPonto+1,valorEnviado.length());
					
		
		
		if (parteDecimal.length() > qtdCasasDecimais) {
			sArredonda = parteDecimal.substring(2,3);
			parteDecimal = parteDecimal.substring(0,qtdCasasDecimais);
			iArredonda = Integer.parseInt(sArredonda);
		}

		if (iArredonda >= 5) {
			iArredonda = Integer.parseInt(parteDecimal);
			iArredonda ++;
			parteDecimal = ""+iArredonda;
		}

		return Float.parseFloat(parteInteira+"."+parteDecimal);
	}
	
	/**
	 * @param email
	 * @return true, se o email for válido e false, caso contrário
	 */
	public boolean validaEmail(String email) {

		String dig, carac = "!#%&*(()=+<>/|[]{},;:?"; //caracteres inválidos

		email = email.trim();

		if (email.isEmpty())  //se vazio, invalida
			return false;

		if(email.indexOf("@") != -1 && email.indexOf(".") != -1) {
		}else
			return false;    //se não contiver @ e . invalida

		for (int i = 0; i < email.length(); i++) {
			dig = email.substring(i, i + 1);
			if (carac.indexOf(dig) == -1) {
				
			}else return false;
		}

		return true;
	}

	/**
	 * retorna o tempo, em dias, entre duas datas
	 */

	public int calcTempo(Date datIni, Date datFim){
		
		
		Calendar dataInicial = new GregorianCalendar();
		dataInicial.setTime(datIni);

		Calendar dataFinal = new GregorianCalendar();
		dataFinal.setTime(datFim);
		 
		//int anoIni = dataInicial.get(Calendar.YEAR);
		//int anoFim = dataFinal.get(Calendar.YEAR);
        long segundos = dataFinal.getTimeInMillis() - 
        		dataInicial.getTimeInMillis();

        if (segundos < 0) {
			return -1;
		}
        
        int dias = (int) (segundos / 86400000);
        
		return dias;
		
	}

	/**
	 * retorna uma data n anos após ou antes a data atual
	 */

	public Date novaData(int n){

		Calendar data = Calendar.getInstance();
		data.add(Calendar.YEAR, n);
		return data.getTime();

	}
	
	/**
	 * Corrige o erro da data gerado pelo conversor padrão
	 * @return: uma data string no formato enviado
	 */
	
	public String corrigeData(Date data, String formato) {

		Calendar date = new GregorianCalendar();
		date.setTime(data);
		date.add(Calendar.DATE, 1);
		DateFormat df = new SimpleDateFormat(formato);
		String dataAnsi = df.format(date.getTime());
		return dataAnsi.substring(0, 10);
	}
	
	public int limiteUsuario(String limiteCadastrado) {

		limiteCadastrado = limiteCadastrado.replaceAll("A", "9");
		limiteCadastrado = limiteCadastrado.replaceAll("B", "8");
		limiteCadastrado = limiteCadastrado.replaceAll("C", "7");
		limiteCadastrado = limiteCadastrado.replaceAll("D", "6");
		limiteCadastrado = limiteCadastrado.replaceAll("E", "5");
		limiteCadastrado = limiteCadastrado.replaceAll("F", "4");
		limiteCadastrado = limiteCadastrado.replaceAll("G", "3");
		limiteCadastrado = limiteCadastrado.replaceAll("H", "2");
		limiteCadastrado = limiteCadastrado.replaceAll("I", "1");
		limiteCadastrado = limiteCadastrado.replaceAll("J", "0");
		return Integer.parseInt(limiteCadastrado);

	}

	/**Verifica se a senha contém pelo memos uma letra maiuscula,  
	 * outra minuscula, um digito e um caractere especial.
	 * O Tamanho mínimo da senha deve ser 4 e o máximo 10 caracteres.
	 * @param senha
	 * @return true contem; false não contem.
	 */
	public boolean validaSenha(String senha) {
		
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

	public String hesh(String texto) throws Exception {
		
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte messageDigest[] = algorithm.digest(texto.getBytes("UTF-8"));
		 
		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
		  hexString.append(String.format("%02X", 0xFF & b));
		}
		
		return hexString.toString();	
		
	}

			
	public String getCriptografia(String senha) throws Exception {
		
        byte[] textoencriptado = criptografica(senha);
        
        senha = "";
        
        for (int i=0; i < textoencriptado.length; i++) {
               senha += textoencriptado[i];
        }

		return senha;
		
	}
	
    private byte[] criptografica(String senha) throws Exception {

        String IV = "A1B0CC99D3E2ZZ88";
        String chaveencriptacao = "0ZX49ABE141112KK";

        Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
        encripta.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
        
       return encripta.doFinal(senha.getBytes("UTF-8"));
        
  }

	
    public double geraToken(){
    	return Math.random() * 289;
    }
	
    public String geraSenha(String codigo){
    	String senha = "";
    	double numero = Math.random() * 289;
    	BigDecimal bd = new BigDecimal(numero).setScale(3, RoundingMode.HALF_EVEN);    	
    	senha = codigo.substring(0, 1).toUpperCase();
    	senha = senha+codigo.substring(1, 3)+"$"+bd;
    	System.out.println(senha);
    	return senha;
    }

	public String dateToStr(String formato) {

		try {
			DateFormat df = new SimpleDateFormat(formato);
			String datStr = df.format(new java.util.Date());
			return datStr;			
		} catch (Exception e) {
			return null;			
		}
	}

	public String doubleToStr(double valor, int qtdCasasDecimais) {

		String formato = "############0.";
		
		for (int i = 0; i < qtdCasasDecimais; i++) {
			formato += "0";
		}
		DecimalFormat df = new DecimalFormat(formato);
		return df.format(valor);
	
	}

}



