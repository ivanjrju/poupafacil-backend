package br.com.poupafacil.back.exceptions;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerControllerException {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<BaseResponseException> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {

		List<String> detalhes = ex.getBindingResult().getAllErrors().stream().map(error -> error.getDefaultMessage())
				.collect(Collectors.toList());

		return new ResponseEntity<BaseResponseException>(
				new BaseResponseException("Erro no contrato", detalhes, LocalDate.now()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<BaseResponseException> handleEntityNotFoundException(EntityNotFoundException ex) {

		return new ResponseEntity<BaseResponseException>(
				new BaseResponseException("Entidade não encontrada", null, LocalDate.now()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PermissaoException.class)
	public ResponseEntity<BaseResponseException> handlePermissaoException(PermissaoException ex) {

		return new ResponseEntity<BaseResponseException>(
				new BaseResponseException(ex.getMensagem(), null, LocalDate.now()), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<BaseResponseException> handleAuthorizationException(AuthorizationException ex) {

		return new ResponseEntity<BaseResponseException>(
				new BaseResponseException(ex.getMensagem(), null, LocalDate.now()), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<BaseResponseException> handleException(Exception ex) {

		return new ResponseEntity<BaseResponseException>(
				new BaseResponseException("Erro interno", null, LocalDate.now()), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<BaseResponseException> handlerRuntimeException(RuntimeException ex) {
		
		return new ResponseEntity<BaseResponseException>(
				new BaseResponseException(ex.getMessage(), null, LocalDate.now()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
