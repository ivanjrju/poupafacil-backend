package br.com.poupafacil.back.entrypoint.grupo.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.poupafacil.back.configs.JwtService;
import br.com.poupafacil.back.entrypoint.grupo.GrupoController;
import br.com.poupafacil.back.entrypoint.grupo.data.request.GrupoRequest;
import br.com.poupafacil.back.entrypoint.grupo.data.response.GrupoPorPessoaResponse;
import br.com.poupafacil.back.entrypoint.grupo.data.response.GrupoResponse;
import br.com.poupafacil.back.entrypoint.grupo.mapper.GrupoEntryPointMapper;
import br.com.poupafacil.back.usecase.grupo.GrupoUseCase;
import br.com.poupafacil.back.usecase.grupo.data.input.GrupoDataInput;
import br.com.poupafacil.back.usecase.grupo.data.output.GrupoDataOutput;
import br.com.poupafacil.back.usecase.pessoa.data.output.PessoaDataOutput;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class GrupoControllerImpl implements GrupoController {

	private GrupoEntryPointMapper grupoEntryPointMapper;
	private GrupoUseCase grupoUseCase;
	private JwtService jwtService;
	
	@Override
	public ResponseEntity<GrupoResponse> criarGrupo(
			GrupoRequest grupoRequest,
			String authorization) throws Exception {
		
		PessoaDataOutput pessoaDataOutput = jwtService.obterPessoa(authorization);
		
		List<String> participantes = grupoRequest.getParticipantes();
		if(!participantes.contains(pessoaDataOutput.getEmail())) {
			participantes.add(pessoaDataOutput.getEmail());
		}
		
		GrupoDataInput grupoDataInput = grupoEntryPointMapper.fromGrupoDataInput(grupoRequest);
		GrupoResponse grupoResponse = grupoEntryPointMapper.toGrupoDataOutput(
				grupoUseCase.criarGrupoUseCase(grupoDataInput));
		return new ResponseEntity<GrupoResponse>(grupoResponse, HttpStatus.CREATED);	
	}

	@Override
	public ResponseEntity<GrupoResponse> buscarGrupo(
			Long idGrupo,
			String authorization) throws Exception {
				
		PessoaDataOutput pessoaDataOutput = jwtService.obterPessoa(authorization);
		
		GrupoDataOutput grupoDataOutput = grupoUseCase.buscarGrupoUseCase(idGrupo);
		grupoDataOutput.getPessoas().stream()
			.filter(pessoa -> pessoa.getIdPessoa() == pessoaDataOutput.getIdPessoa())
			.findFirst()
			.orElseThrow();
		
		GrupoResponse grupoResponse = grupoEntryPointMapper.toGrupoDataOutput(grupoDataOutput);
		return new ResponseEntity<GrupoResponse>(grupoResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<GrupoPorPessoaResponse>> buscarGruposPorPessoa(
			String authorization) throws Exception {
		
		PessoaDataOutput pessoaDataOutput = jwtService.obterPessoa(authorization);

		List<GrupoDataOutput> gruposDataOutput = grupoUseCase.buscarGruposPorPessoaUseCase(pessoaDataOutput.getIdPessoa());
		List<GrupoPorPessoaResponse> gruposResponse = grupoEntryPointMapper.toGruposDataOutput(gruposDataOutput);
		return new ResponseEntity<List<GrupoPorPessoaResponse>>(gruposResponse, HttpStatus.OK);
	}
}
