package br.com.poupafacil.back.usecase.grupo.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.poupafacil.back.gateway.grupo.model.GrupoModel;
import br.com.poupafacil.back.usecase.grupo.data.input.GrupoDataInput;
import br.com.poupafacil.back.usecase.grupo.data.output.GrupoDataOutput;
import br.com.poupafacil.back.usecase.pessoa.mapper.PessoaUseCaseMapper;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class GrupoUseCaseMapper {

	private PessoaUseCaseMapper pessoaUseCaseMapper;
	
	public GrupoModel fromGrupoModel(GrupoDataInput grupoDataInput) {
		
		return GrupoModel.builder()
				.nome(grupoDataInput.getNome())
				.build();
	}

	public GrupoDataOutput toGrupoModel(GrupoModel grupoModel) {
		
		if(Objects.isNull(grupoModel))
			return null;
		
		return GrupoDataOutput.builder()
				.idGrupo(grupoModel.getIdGrupo())
				.nome(grupoModel.getNome())
				.pessoas(pessoaUseCaseMapper.toPessoasModel(grupoModel.getPessoas()))
				.build();
	}

	public GrupoModel toGrupoDataOutput(GrupoDataOutput grupoDataOutput) {

		return GrupoModel.builder()
				.idGrupo(grupoDataOutput.getIdGrupo())
				.nome(grupoDataOutput.getNome())
				.pessoas(pessoaUseCaseMapper.toPessoaDataOutput(grupoDataOutput.getPessoas()))
				.build();
	}

	public List<GrupoDataOutput> toGrupoModel(List<GrupoModel> gruposModel) {
		
		return gruposModel.stream().map(grupoModel -> toGrupoModel(grupoModel)).collect(Collectors.toList());
	}
}
