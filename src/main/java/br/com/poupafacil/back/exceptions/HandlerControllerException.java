package br.com.poupafacil.back.exceptions;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerControllerException {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<BaseException> handleMethodArgumentNotValidException(
	  MethodArgumentNotValidException ex) {
		
		List<String> detalhes = ex.getBindingResult().getAllErrors().stream()
				.map(error -> error.getDefaultMessage())
				.collect(Collectors.toList());
		
		return new ResponseEntity<BaseException>(BaseException.builder()
	    		.mensagem("Erro no contrato")
	    		.detalhes(detalhes)
	    		.data(LocalDate.now())
	    		.build(),
	    		HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<BaseException> handleEntityNotFoundException(
			EntityNotFoundException ex) {
		
		return new ResponseEntity<BaseException>(BaseException.builder()
				.mensagem("Entidade não encontrada")
				.detalhes(null)
				.data(LocalDate.now())
				.build(),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<BaseException> handleEntityNotFoundException(
			Exception ex) {
		
		return new ResponseEntity<BaseException>(BaseException.builder()
				.mensagem("Erro interno")
				.detalhes(null)
				.data(LocalDate.now())
				.build(),
				HttpStatus.NOT_FOUND);
	}
}
