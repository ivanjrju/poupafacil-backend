package br.com.poupafacil.back.entrypoint.grupo.data.response;

import java.util.List;

import br.com.poupafacil.back.entrypoint.pessoa.data.response.PessoaResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GrupoResponse {

	private Long idGrupo;
	private String nome;
	private List<PessoaResponse> pessoas;
}
