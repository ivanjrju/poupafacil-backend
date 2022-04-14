package br.com.poupafacil.back.entrypoint.pessoa.data.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PessoaRequest {

	@NotEmpty(message = "'nome' é obrigatório")
	private String nome;

	@NotEmpty(message = "'email' é obrigatório")
	private String email;

	@NotNull(message = "'renda' é obrigatório")
	private BigDecimal renda;
}
