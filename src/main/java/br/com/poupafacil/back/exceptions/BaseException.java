package br.com.poupafacil.back.exceptions;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BaseException {

	private String mensagem;
	private List<String> detalhes;
	private LocalDate data;
	
}
