package br.com.poupafacil.back.entrypoint.pessoa.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.poupafacil.back.configs.JwtService;
import br.com.poupafacil.back.entrypoint.pessoa.PessoaController;
import br.com.poupafacil.back.entrypoint.pessoa.data.request.PessoaRequest;
import br.com.poupafacil.back.entrypoint.pessoa.data.response.PessoaResponse;
import br.com.poupafacil.back.entrypoint.pessoa.mapper.PessoaEntryPointMapper;
import br.com.poupafacil.back.usecase.pessoa.PessoaUseCase;
import br.com.poupafacil.back.usecase.pessoa.data.input.PessoaDataInput;
import br.com.poupafacil.back.usecase.pessoa.data.output.PessoaDataOutput;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor 
public class PessoaControllerImpl implements PessoaController {

	private PessoaEntryPointMapper pessoaEntryPointMapper;
	private PessoaUseCase pessoaUseCase;
	private JwtService jwtService;
	
	@Override
	public ResponseEntity<PessoaResponse> criarPessoa(
			PessoaRequest pessoaRequest) {
		
		PessoaDataInput pessoaDataInput = pessoaEntryPointMapper.fromPessoaDataInput(pessoaRequest);
		PessoaResponse pessoaResponse = pessoaEntryPointMapper.toPessoaResponse(
				pessoaUseCase.criarPessoaUseCase(pessoaDataInput));
		return new ResponseEntity<PessoaResponse>(pessoaResponse, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<PessoaResponse> buscarPessoa(String authorization) throws Exception {			
		
		PessoaDataOutput pessoaDataOutput = jwtService.obterPessoa(authorization);
		
		PessoaResponse pessoaResponse = pessoaEntryPointMapper.toPessoaResponse(pessoaDataOutput);
		return new ResponseEntity<PessoaResponse>(pessoaResponse, HttpStatus.OK);
	}
}
