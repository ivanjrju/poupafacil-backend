package br.com.poupafacil.back.entrypoint.grupo.data.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GrupoPorPessoaResponse {

	private Long idGrupo;
	private String nome;
}
