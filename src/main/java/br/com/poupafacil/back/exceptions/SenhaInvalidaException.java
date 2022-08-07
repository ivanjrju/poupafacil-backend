package br.com.poupafacil.back.exceptions;

public class SenhaInvalidaException extends RuntimeException {
    
	private static final long serialVersionUID = -9035137588897556378L;

	public SenhaInvalidaException() {
        super("Senha inválida");
    }
}