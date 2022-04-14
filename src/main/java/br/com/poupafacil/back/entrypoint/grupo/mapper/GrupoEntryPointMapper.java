package br.com.poupafacil.back.entrypoint.grupo.mapper;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import br.com.poupafacil.back.entrypoint.grupo.data.request.GrupoRequest;
import br.com.poupafacil.back.entrypoint.grupo.data.response.GrupoResponse;
import br.com.poupafacil.back.entrypoint.pessoa.mapper.PessoaEntryPointMapper;
import br.com.poupafacil.back.usecase.grupo.data.input.GrupoDataInput;
import br.com.poupafacil.back.usecase.grupo.data.output.GrupoDataOutput;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class GrupoEntryPointMapper {

	private PessoaEntryPointMapper pessoaEntryPointMapper;
	
	public GrupoDataInput fromGrupoDataInput(@Valid GrupoRequest grupoRequest) {
		
		return GrupoDataInput.builder()
				.nome(grupoRequest.getNome())
				.participantes(grupoRequest.getParticipantes())
				.build();
	}

	public GrupoResponse toGrupoDataOutput(GrupoDataOutput grupoDataOutput) {
	
		if(Objects.isNull(grupoDataOutput))
			return null;
		
		return GrupoResponse.builder()
				.idGrupo(grupoDataOutput.getIdGrupo())
				.nome(grupoDataOutput.getNome())
				.pessoas(pessoaEntryPointMapper.toPessoasResponse(grupoDataOutput.getPessoas()))
				.build();
	}
}
