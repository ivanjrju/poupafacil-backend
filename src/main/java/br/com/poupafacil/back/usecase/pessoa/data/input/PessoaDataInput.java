package br.com.poupafacil.back.usecase.pessoa.data.input;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PessoaDataInput {

	private String email;
	private String nome;
	private BigDecimal renda;
}
