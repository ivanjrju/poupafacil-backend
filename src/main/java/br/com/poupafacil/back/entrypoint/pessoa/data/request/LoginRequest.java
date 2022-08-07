package br.com.poupafacil.back.entrypoint.pessoa.data.request;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class LoginRequest {

	@NotEmpty(message = "'email' é obrigatório")
	private String email;
	
	@NotEmpty(message = "'senha' é obrigatório")
	private String senha;
}
