package br.com.poupafacil.back.entrypoint.despesa.impl;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
import br.com.poupafacil.back.usecase.pessoa.data.output.PessoaDataOutput;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DespesaControllerImpl implements DespesaController {

	private DespesaEntryPointMapper despesaEntryPointMapper;
	private DespesaUseCase despesaUseCase;

	private ObjectMapper mapper;

	@Override
	public ResponseEntity<List<DespesaResponse>> criarDespesa(
			DespesaRequest despesaRequest,
			HttpServletRequest request) throws Exception {

		PessoaDataOutput pessoaDataOutput = mapper.readValue(request.getAttribute("pessoa").toString(),
				PessoaDataOutput.class);

		DespesaDataInput despesaDataInput = despesaEntryPointMapper.fromDespesaUseCaseInput(despesaRequest);
		List<DespesaResponse> despesasResponse = despesaEntryPointMapper
				.toDespesasUseCaseOutput(despesaUseCase.criarDespesaUseCase(despesaDataInput, pessoaDataOutput));

		return new ResponseEntity<List<DespesaResponse>>(despesasResponse, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<ConsolidadoMesDespesaResponse>> buscarDespesasPorGrupo(
			Long idGrupo,
			Periodo periodo,
			HttpServletRequest request) throws JsonMappingException, JsonProcessingException {

		PessoaDataOutput pessoaDataOutput = mapper.readValue(request.getAttribute("pessoa").toString(),
				PessoaDataOutput.class);

		periodo = validarPeriodo(periodo);
		return consolidadosMesesDespesasResponse(
				despesaUseCase.buscarDespesasPorGrupoUseCases(idGrupo, periodo, pessoaDataOutput));
	}

	@Override
	public ResponseEntity<List<ConsolidadoMesDespesaResponse>> buscarDespesasPorPessoa(
			Periodo periodo,
			Long idGrupo,
			HttpServletRequest request) throws JsonMappingException, JsonProcessingException {

		PessoaDataOutput pessoaDataOutput = mapper.readValue(request.getAttribute("pessoa").toString(), PessoaDataOutput.class);
		
		periodo = validarPeriodo(periodo);
		return consolidadosMesesDespesasResponse(
				despesaUseCase.buscarDespesasPorPessoaUseCases(pessoaDataOutput.getIdPessoa(), periodo, idGrupo));
	}

	@Override
	public ResponseEntity<List<ConsolidadoEstimativaDespesaResponse>> buscarDespesasParaEstimativas(
			HttpServletRequest request) throws JsonMappingException, JsonProcessingException {

		PessoaDataOutput pessoaDataOutput = mapper.readValue(request.getAttribute("pessoa").toString(), PessoaDataOutput.class);
		
		List<ConsolidadoMesDespesaDataOutput> consolidadoMeses = despesaUseCase
				.buscarDespesasParaEstimativasUseCases(pessoaDataOutput.getIdPessoa());
		return new ResponseEntity<List<ConsolidadoEstimativaDespesaResponse>>(
				despesaEntryPointMapper.toConsolidadoEstimativasDespesaResponse(consolidadoMeses), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ConsolidadoTagResponse>> buscarTagsPorDespesas(
			HttpServletRequest request) throws JsonMappingException, JsonProcessingException {

		PessoaDataOutput pessoaDataOutput = mapper.readValue(request.getAttribute("pessoa").toString(), PessoaDataOutput.class);
		
		List<ConsolidadoTagOutput> consolidadoTagOutput = despesaUseCase.buscarTagsPorDespesasUseCases(pessoaDataOutput.getIdPessoa());
		return new ResponseEntity<List<ConsolidadoTagResponse>>(
				despesaEntryPointMapper.toConsolidadoTagOutput(consolidadoTagOutput), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<String> removerDespesasPorIdCorrelacao(
			String idCorrelacao,
			HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
	
		PessoaDataOutput pessoaDataOutput = mapper.readValue(request.getAttribute("pessoa").toString(), PessoaDataOutput.class);

		despesaUseCase.removerDespesasPorIdCorrelacaoUseCase(idCorrelacao, pessoaDataOutput);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	private Periodo validarPeriodo(Periodo periodo) {
		if (Objects.isNull(periodo))
			return Periodo.MENSAL;
		return periodo;
	}

	private ResponseEntity<List<ConsolidadoMesDespesaResponse>> consolidadosMesesDespesasResponse(
			List<ConsolidadoMesDespesaDataOutput> consolidadoMeses) {
		return new ResponseEntity<List<ConsolidadoMesDespesaResponse>>(
				despesaEntryPointMapper.toConsolidadoMesesDespesasDataOutput(consolidadoMeses), HttpStatus.OK);
	}
}
