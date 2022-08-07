package br.com.poupafacil.back.entrypoint.pessoa.amizade.data.request;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class AmizadeRequest {

	@NotEmpty(message = "'email' é obrigatório")
	private String email;
	
}
