package br.com.poupafacil.back.entrypoint.grupo.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	private ObjectMapper mapper;
	
	@Override
	public ResponseEntity<GrupoResponse> criarGrupo(GrupoRequest grupoRequest, HttpServletRequest request) throws Exception {
		
		PessoaDataOutput pessoaDataOutput = mapper.readValue(request.getAttribute("pessoa").toString(), PessoaDataOutput.class);
		
		List<Long> participantes = grupoRequest.getParticipantes();
		if(!participantes.contains(pessoaDataOutput.getIdPessoa())) {
			participantes.add(pessoaDataOutput.getIdPessoa());
		}
		
		GrupoDataInput grupoDataInput = grupoEntryPointMapper.fromGrupoDataInput(grupoRequest);
		GrupoResponse grupoResponse = grupoEntryPointMapper.toGrupoDataOutput(
				grupoUseCase.criarGrupoUseCase(grupoDataInput));
		return new ResponseEntity<GrupoResponse>(grupoResponse, HttpStatus.CREATED);	
	}

	@Override
	public ResponseEntity<GrupoResponse> buscarGrupo(Long idGrupo, HttpServletRequest request) throws Exception {
				
		PessoaDataOutput pessoaDataOutput = mapper.readValue(request.getAttribute("pessoa").toString(), PessoaDataOutput.class);
		
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
			HttpServletRequest request) throws Exception {
		
		PessoaDataOutput pessoaDataOutput = mapper.readValue(request.getAttribute("pessoa").toString(), PessoaDataOutput.class);

		List<GrupoDataOutput> gruposDataOutput = grupoUseCase.buscarGruposPorPessoaUseCase(pessoaDataOutput.getIdPessoa());
		List<GrupoPorPessoaResponse> gruposResponse = grupoEntryPointMapper.toGruposDataOutput(gruposDataOutput);
		return new ResponseEntity<List<GrupoPorPessoaResponse>>(gruposResponse, HttpStatus.OK);
	}
}
