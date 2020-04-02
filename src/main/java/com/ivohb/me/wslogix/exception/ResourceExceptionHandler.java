package com.ivohb.me.wslogix.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

	//ocorre quando uma leitura não retorna dados
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(
			ObjectNotFoundException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(
			System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), 
			"nao_encontrado", e.getMessage(),  request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	//Ocorre quando um objeto não pode ser escluido por possuir relacionamentos
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(
			DataIntegrityException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(
			System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), 
			"erro_de_integridade", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	//Ocorre quando uma objeto não passar na validação sintática
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(
			MethodArgumentNotValidException e, HttpServletRequest request) {
		
		ValidationError err = new ValidationError(
			System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), 
			"erro_de_validacao", "erro_de_validacao", request.getRequestURI());
		
		//obtém a lista de erros geradas pelo framwork
		
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}		
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}
	
	//Ocorre quando um usuário tentar acessar um end-point nao autorizado
	
	/*@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(
			AuthorizationException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(
				System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(), 
				"acesso_negado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}*/

	/*@ExceptionHandler(FileException.class)
	public ResponseEntity<StandardError> file(FileException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(
				System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), 
				"Erro de arquivo", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}*/
	
	/*@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<StandardError> amazonService(AmazonServiceException e, HttpServletRequest request) {
		
		HttpStatus code = HttpStatus.valueOf(e.getErrorCode());
		StandardError err = new StandardError(
				System.currentTimeMillis(), code.value(), 
				"Erro Amazon Service", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(code).body(err);
	}*/

	/*@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<StandardError> amazonClient(AmazonClientException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(
				System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), 
				"Erro Amazon Client", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}*/

	/*@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<StandardError> amazonS3(AmazonS3Exception e, HttpServletRequest request) {
		
		StandardError err = new StandardError(
				System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), 
				"Erro S3", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}*/
}
