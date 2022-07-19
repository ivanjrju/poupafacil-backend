package br.com.poupafacil.back.entrypoint.grupo.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.poupafacil.back.entrypoint.grupo.GrupoController;
import br.com.poupafacil.back.entrypoint.grupo.data.request.GrupoRequest;
import br.com.poupafacil.back.entrypoint.grupo.data.response.GrupoPorPessoaResponse;
import br.com.poupafacil.back.entrypoint.grupo.data.response.GrupoResponse;
import br.com.poupafacil.back.entrypoint.grupo.mapper.GrupoEntryPointMapper;
import br.com.poupafacil.back.usecase.grupo.GrupoUseCase;
import br.com.poupafacil.back.usecase.grupo.data.input.GrupoDataInput;
import br.com.poupafacil.back.usecase.grupo.data.output.GrupoDataOutput;
import br.com.poupafacil.back.usecase.permissao.PermissaoUseCase;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class GrupoControllerImpl implements GrupoController {

	private GrupoEntryPointMapper grupoEntryPointMapper;
	private GrupoUseCase grupoUseCase;
	
	private PermissaoUseCase permissaoUseCase;
	
	@Override
	public ResponseEntity<GrupoResponse> criarGrupo(GrupoRequest grupoRequest, String authorization) throws Exception {
		
//		permissaoUseCase.permitirAcessoParticipantesGrupo(grupoRequest.getParticipantes(), authorization);
		
		GrupoDataInput grupoDataInput = grupoEntryPointMapper.fromGrupoDataInput(grupoRequest);
		GrupoResponse grupoResponse = grupoEntryPointMapper.toGrupoDataOutput(
				grupoUseCase.criarGrupoUseCase(grupoDataInput));
		return new ResponseEntity<GrupoResponse>(grupoResponse, HttpStatus.CREATED);	
	}

	@Override
	public ResponseEntity<GrupoResponse> buscarGrupo(Long idGrupo, String authorization) throws Exception {
		
		GrupoDataOutput grupoDataOutput = grupoUseCase.buscarGrupoUseCase(idGrupo);
		
		List<Long> idPessoas = grupoDataOutput.getPessoas().stream().map(pessoa -> pessoa.getIdPessoa()).collect(Collectors.toList());
		
//		permissaoUseCase.permitirAcessoParticipantesGrupo(idPessoas, authorization);
		
		GrupoResponse grupoResponse = grupoEntryPointMapper.toGrupoDataOutput(grupoDataOutput);
		return new ResponseEntity<GrupoResponse>(grupoResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<GrupoPorPessoaResponse>> buscarGruposPorPessoa(Long idPessoa,
			String authorization) throws Exception {
		
//		permissaoUseCase.permitirAcessoPessoa(idPessoa, authorization);

		List<GrupoDataOutput> gruposDataOutput = grupoUseCase.buscarGruposPorPessoaUseCase(idPessoa);
		List<GrupoPorPessoaResponse> gruposResponse = grupoEntryPointMapper.toGruposDataOutput(gruposDataOutput);
		return new ResponseEntity<List<GrupoPorPessoaResponse>>(gruposResponse, HttpStatus.OK);
	}
}
