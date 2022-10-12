package br.com.poupafacil.back.usecase.grupo.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.poupafacil.back.gateway.grupo.GrupoRepository;
import br.com.poupafacil.back.gateway.grupo.model.GrupoModel;
import br.com.poupafacil.back.gateway.pessoa.model.PessoaModel;
import br.com.poupafacil.back.usecase.grupo.GrupoUseCase;
import br.com.poupafacil.back.usecase.grupo.data.input.GrupoDataInput;
import br.com.poupafacil.back.usecase.grupo.data.output.GrupoDataOutput;
import br.com.poupafacil.back.usecase.grupo.mapper.GrupoUseCaseMapper;
import br.com.poupafacil.back.usecase.pessoa.PessoaUseCase;
import br.com.poupafacil.back.usecase.pessoa.data.output.PessoaDataOutput;
import br.com.poupafacil.back.usecase.pessoa.mapper.PessoaUseCaseMapper;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class GrupoUseCaseImpl implements GrupoUseCase {

	private GrupoUseCaseMapper grupoUseCaseMapper;
	private GrupoRepository grupoRepository;
	
	private PessoaUseCase pessoaUseCase;
	private PessoaUseCaseMapper pessoaUseCaseMapper;
	
	@Override
	public GrupoDataOutput criarGrupoUseCase(GrupoDataInput grupoDataInput) {
		
		GrupoModel grupoModel = grupoUseCaseMapper.fromGrupoModel(grupoDataInput);
		incluirPessoasNoGrupo(grupoDataInput, grupoModel);
		
		return grupoUseCaseMapper.toGrupoModel(grupoRepository.save(grupoModel));
	}

	private void incluirPessoasNoGrupo(GrupoDataInput grupoDataInput, GrupoModel grupoModel) {
		
		List<PessoaDataOutput> pessoasDataOutput = grupoDataInput.getParticipantes().stream()
				.map(email -> pessoaUseCase.buscarPessoaPorEmailUseCase(email))
				.collect(Collectors.toList());
			
		List<PessoaModel> pessoasModel = pessoaUseCaseMapper.toPessoaDataOutput(pessoasDataOutput);
		grupoModel.setPessoas(pessoasModel);
	}

	@Override
	public GrupoDataOutput buscarGrupoUseCase(Long idGrupo) {
		
		GrupoModel grupoModel = grupoRepository.getById(idGrupo);
		return grupoUseCaseMapper.toGrupoModel(grupoModel);
	}
	
	@Override
	public List<GrupoDataOutput> buscarGruposPorPessoaUseCase(Long idPessoa) {
		
		List<GrupoModel> gruposModel = grupoRepository.findByPessoasIdPessoa(idPessoa);
		return grupoUseCaseMapper.toGrupoModel(gruposModel);
	}
}
