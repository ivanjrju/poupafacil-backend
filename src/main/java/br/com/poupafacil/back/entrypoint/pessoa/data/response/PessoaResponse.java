package br.com.poupafacil.back.entrypoint.pessoa.data.response;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PessoaResponse {

	private Long idPessoa;
	private String nome;
	private String email;
	private BigDecimal renda;
}
