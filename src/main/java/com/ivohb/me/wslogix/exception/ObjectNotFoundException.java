package com.ivohb.me.wslogix.exception;

/*Utilização de exception personalizada:
 * 1 - criar a classe ???Exception 
 * 2 - alterar o serviço para retornar o obj ou a exception correspondente
 * 3 - implemetação do Handler - manipulador de exception do recurso
 * 4 - Implemetar o obj StandardError, com os atributos que serão populados e
 *     retornados ao client, quando a excption ocorrer
 */

public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}

	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
