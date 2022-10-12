package br.com.poupafacil.back.entrypoint.pessoa.amizade.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.poupafacil.back.configs.JwtService;
import br.com.poupafacil.back.entrypoint.pessoa.amizade.AmizadeController;
import br.com.poupafacil.back.entrypoint.pessoa.amizade.data.request.AmizadeRequest;
import br.com.poupafacil.back.entrypoint.pessoa.amizade.data.response.AmizadeResponse;
import br.com.poupafacil.back.entrypoint.pessoa.amizade.mapper.AmizadeEntryPointMapper;
import br.com.poupafacil.back.usecase.pessoa.amizade.AmizadeUseCase;
import br.com.poupafacil.back.usecase.pessoa.amizade.data.input.AmizadeDataInput;
import br.com.poupafacil.back.usecase.pessoa.amizade.data.output.AmizadeDataOuput;
import br.com.poupafacil.back.usecase.pessoa.data.output.PessoaDataOutput;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor 
public class AmizadeControllerImpl implements AmizadeController {
	
	private AmizadeEntryPointMapper amizadeEntryPointMapper;
	private AmizadeUseCase amizadeUseCase;
	private JwtService jwtService;
	
	@Override
	public ResponseEntity<Void> solicitarAmizade(@Valid AmizadeRequest amizadeRequest,
			String authorization) {
		
		PessoaDataOutput pessoaDataOutput = jwtService.obterPessoa(authorization);
		
		AmizadeDataInput amizadeDataInput = amizadeEntryPointMapper.fromAmizadeDataInput(amizadeRequest, pessoaDataOutput);
		amizadeUseCase.solicitarAmizade(amizadeDataInput);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<List<AmizadeResponse>> listarPedidoAmizade(String authorization) {
		
		PessoaDataOutput pessoaDataOutput = jwtService.obterPessoa(authorization);
		List<AmizadeDataOuput> amizadesDataOuput = amizadeUseCase.listarPedidoAmizade(pessoaDataOutput.getEmail());
		return new ResponseEntity<List<AmizadeResponse>>(amizadeEntryPointMapper.toAmizadesResponse(amizadesDataOuput), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<AmizadeResponse>> aceitarPedidoAmizade(
			String email,
			String authorization) {
		
		PessoaDataOutput pessoaDataOutput = jwtService.obterPessoa(authorization);
		List<AmizadeDataOuput> amizadesDataOuput = amizadeUseCase.aceitarPedidoAmizade(pessoaDataOutput.getEmail(), email);
		return new ResponseEntity<List<AmizadeResponse>>(amizadeEntryPointMapper.toAmizadesResponse(amizadesDataOuput), HttpStatus.OK);
	}
}
