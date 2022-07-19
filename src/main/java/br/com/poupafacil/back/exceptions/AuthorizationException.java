package br.com.poupafacil.back.exceptions;

import java.io.IOException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizationException extends IOException {

	private static final long serialVersionUID = 8613107957398848520L;

	public AuthorizationException(String mensagem) {
		super(mensagem);
	}
	
	private String mensagem;
}
