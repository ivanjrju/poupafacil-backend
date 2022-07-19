package br.com.poupafacil.back.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PermissaoException extends Exception {

	private static final long serialVersionUID = -3989286683653139515L;

	private String mensagem;
}
