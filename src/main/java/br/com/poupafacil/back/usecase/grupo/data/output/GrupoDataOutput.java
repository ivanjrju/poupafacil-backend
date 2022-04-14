package br.com.poupafacil.back.usecase.grupo.data.output;

import java.util.List;

import br.com.poupafacil.back.usecase.pessoa.data.output.PessoaDataOutput;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GrupoDataOutput {

	private Long idGrupo;
	private String nome;
	private List<PessoaDataOutput> pessoas;
}
