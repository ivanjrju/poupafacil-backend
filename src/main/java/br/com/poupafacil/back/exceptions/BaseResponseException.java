package br.com.poupafacil.back.exceptions;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseResponseException {

	private String mensagem;
	private List<String> detalhes;	
	private LocalDate data;
}
