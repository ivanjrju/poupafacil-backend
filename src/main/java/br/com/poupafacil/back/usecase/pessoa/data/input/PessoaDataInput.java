package br.com.poupafacil.back.usecase.pessoa.data.input;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PessoaDataInput {

	private String nome;
	private String email;
	private String senha;
	private BigDecimal renda;
}
