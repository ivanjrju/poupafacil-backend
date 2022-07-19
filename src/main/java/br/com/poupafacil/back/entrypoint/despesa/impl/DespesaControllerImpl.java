package br.com.poupafacil.back.entrypoint.despesa.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.poupafacil.back.commons.enums.Periodo;
import br.com.poupafacil.back.entrypoint.despesa.DespesaController;
import br.com.poupafacil.back.entrypoint.despesa.data.request.DespesaRequest;
import br.com.poupafacil.back.entrypoint.despesa.data.response.ConsolidadoEstimativaDespesaResponse;
import br.com.poupafacil.back.entrypoint.despesa.data.response.ConsolidadoMesDespesaResponse;
import br.com.poupafacil.back.entrypoint.despesa.data.response.ConsolidadoTagResponse;
import br.com.poupafacil.back.entrypoint.despesa.data.response.DespesaResponse;
import br.com.poupafacil.back.entrypoint.despesa.mapper.DespesaEntryPointMapper;
import br.com.poupafacil.back.usecase.despesa.DespesaUseCase;
import br.com.poupafacil.back.usecase.despesa.data.input.DespesaDataInput;
import br.com.poupafacil.back.usecase.despesa.data.output.ConsolidadoMesDespesaDataOutput;
import br.com.poupafacil.back.usecase.despesa.data.output.ConsolidadoTagOutput;
import br.com.poupafacil.back.usecase.permissao.PermissaoUseCase;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DespesaControllerImpl implements DespesaController {

	private DespesaEntryPointMapper despesaEntryPointMapper;
	private DespesaUseCase despesaUseCase;
	
	private PermissaoUseCase permissaoUseCase;

	@Override
	public ResponseEntity<List<DespesaResponse>> criarDespesa(DespesaRequest despesaRequest, String authorization) throws Exception {

//		if(Objects.nonNull(despesaRequest.getIdGrupo()))
//			permissaoUseCase.permitirAcessoGrupo(despesaRequest.getProprietarioDespesa(), despesaRequest.getIdGrupo(), authorization);
//		else
//			permissaoUseCase.permitirAcessoPessoa(despesaRequest.getProprietarioDespesa(), authorization);
		
		DespesaDataInput despesaDataInput = despesaEntryPointMapper.fromDespesaUseCaseInput(despesaRequest);
		List<DespesaResponse> despesasResponse = despesaEntryPointMapper.toDespesasUseCaseOutput(
				despesaUseCase.criarDespesaUseCase(despesaDataInput));
		
		return new ResponseEntity<List<DespesaResponse>>(despesasResponse, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<ConsolidadoMesDespesaResponse>> buscarDespesasPorGrupo(Long idGrupo, Periodo periodo) {
		
		periodo = validarPeriodo(periodo);
		return consolidadosMesesDespesasResponse(despesaUseCase.buscarDespesasPorGrupoUseCases(idGrupo, periodo));
	}

	@Override
	public ResponseEntity<List<ConsolidadoMesDespesaResponse>> buscarDespesasPorPessoa(Long idPessoa, Periodo periodo, Long idGrupo) {
		
		periodo = validarPeriodo(periodo);
		return consolidadosMesesDespesasResponse(despesaUseCase.buscarDespesasPorPessoaUseCases(idPessoa, periodo, idGrupo));
	}

	@Override
	public ResponseEntity<List<ConsolidadoEstimativaDespesaResponse>> buscarDespesasParaEstimativas(Long idPessoa) {
		
		List<ConsolidadoMesDespesaDataOutput> consolidadoMeses = despesaUseCase.buscarDespesasParaEstimativasUseCases(idPessoa);
		return new ResponseEntity<List<ConsolidadoEstimativaDespesaResponse>>(
				despesaEntryPointMapper.toConsolidadoEstimativasDespesaResponse(consolidadoMeses), 
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> removerDespesasPorIdCorrelacao(String idCorrelacao) {
	
		despesaUseCase.removerDespesasPorIdCorrelacaoUseCase(idCorrelacao);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	private Periodo validarPeriodo(Periodo periodo) {
		if(Objects.isNull(periodo))
			return Periodo.MENSAL;
		return periodo;
	}
	
	private ResponseEntity<List<ConsolidadoMesDespesaResponse>> consolidadosMesesDespesasResponse(
			List<ConsolidadoMesDespesaDataOutput> consolidadoMeses) {
		return new ResponseEntity<List<ConsolidadoMesDespesaResponse>>(
				despesaEntryPointMapper.toConsolidadoMesesDespesasDataOutput(consolidadoMeses), 
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ConsolidadoTagResponse>> buscarTagsPorDespesas(Long idPessoa) {

		List<ConsolidadoTagOutput> consolidadoTagOutput = despesaUseCase.buscarTagsPorDespesasUseCases(idPessoa);
		return new ResponseEntity<List<ConsolidadoTagResponse>>(
				despesaEntryPointMapper.toConsolidadoTagOutput(consolidadoTagOutput),
				HttpStatus.OK);
	}
}
