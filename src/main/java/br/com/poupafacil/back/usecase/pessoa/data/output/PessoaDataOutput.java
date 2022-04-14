package br.com.poupafacil.back.usecase.pessoa.data.output;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PessoaDataOutput {

	private Long idPessoa;
	private String email;
	private String nome;
	private BigDecimal renda;
}
