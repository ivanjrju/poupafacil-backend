package br.com.poupafacil.back.entrypoint.pessoa.amizade.data.request;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class AmizadeRequest {

	@NotEmpty(message = "'email solicitado' é obrigatório")
	private String emailSolicitado;
	
}
