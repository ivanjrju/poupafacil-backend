package br.com.poupafacil.back.entrypoint.grupo.impl;

import java.util.List;

import javax.validation.Valid;

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
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class GrupoControllerImpl implements GrupoController {

	private GrupoEntryPointMapper grupoEntryPointMapper;
	private GrupoUseCase grupoUseCase;
	
	@Override
	public ResponseEntity<GrupoResponse> criarGrupo(@Valid GrupoRequest grupoRequest) {
		
		GrupoDataInput grupoDataInput = grupoEntryPointMapper.fromGrupoDataInput(grupoRequest);
		GrupoResponse grupoResponse = grupoEntryPointMapper.toGrupoDataOutput(
				grupoUseCase.criarGrupoUseCase(grupoDataInput));
		return new ResponseEntity<GrupoResponse>(grupoResponse, HttpStatus.CREATED);	
	}

	@Override
	public ResponseEntity<GrupoResponse> buscarGrupo(Long idGrupo) {
		
		GrupoDataOutput grupoDataOutput = grupoUseCase.buscarGrupoUseCase(idGrupo);
		GrupoResponse grupoResponse = grupoEntryPointMapper.toGrupoDataOutput(grupoDataOutput);
		return new ResponseEntity<GrupoResponse>(grupoResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<GrupoPorPessoaResponse>> buscarGruposPorPessoa(Long idPessoa) {

		List<GrupoDataOutput> gruposDataOutput = grupoUseCase.buscarGruposPorPessoaUseCase(idPessoa);
		List<GrupoPorPessoaResponse> gruposResponse = grupoEntryPointMapper.toGruposDataOutput(gruposDataOutput);
		return new ResponseEntity<List<GrupoPorPessoaResponse>>(gruposResponse, HttpStatus.OK);
	}
}
