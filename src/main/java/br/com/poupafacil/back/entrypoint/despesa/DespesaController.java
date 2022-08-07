package br.com.poupafacil.back.entrypoint.despesa;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.poupafacil.back.commons.enums.Periodo;
import br.com.poupafacil.back.entrypoint.despesa.data.request.DespesaRequest;
import br.com.poupafacil.back.entrypoint.despesa.data.response.ConsolidadoEstimativaDespesaResponse;
import br.com.poupafacil.back.entrypoint.despesa.data.response.ConsolidadoMesDespesaResponse;
import br.com.poupafacil.back.entrypoint.despesa.data.response.ConsolidadoTagResponse;
import br.com.poupafacil.back.entrypoint.despesa.data.response.DespesaResponse;

@RestController
@RequestMapping("/api/despesas")
public interface DespesaController {

	@CrossOrigin
	@PostMapping
	public ResponseEntity<List<DespesaResponse>> criarDespesa(
			@RequestBody @Valid DespesaRequest despesaRequest,
			@RequestHeader("Authorization") String authorization) throws Exception;

	@CrossOrigin
	@GetMapping("/grupo/{idGrupo}")
	public ResponseEntity<List<ConsolidadoMesDespesaResponse>> buscarDespesasPorGrupo(
			@PathVariable("idGrupo") Long idGrupo,
			@PathParam("periodo") Periodo periodo,
			@RequestHeader("Authorization") String authorization) throws JsonMappingException, JsonProcessingException;

	@CrossOrigin
	@GetMapping("/pessoa")
	public ResponseEntity<List<ConsolidadoMesDespesaResponse>> buscarDespesasPorPessoa(
			@PathParam("periodo") Periodo periodo,
			@PathParam("idGrupo") Long idGrupo,
			@RequestHeader("Authorization") String authorization) throws JsonMappingException, JsonProcessingException;
	
	@CrossOrigin
	@GetMapping("/pessoa/estimativas")
	public ResponseEntity<List<ConsolidadoEstimativaDespesaResponse>> buscarDespesasParaEstimativas(
			@RequestHeader("Authorization") String authorization) throws JsonMappingException, JsonProcessingException;
	
	@CrossOrigin
	@GetMapping("/tags/pessoa")
	public ResponseEntity<List<ConsolidadoTagResponse>> buscarTagsPorDespesas(
			@RequestHeader("Authorization") String authorization) throws JsonMappingException, JsonProcessingException;
	
	@CrossOrigin
	@DeleteMapping("/{idCorrelacao}")
	public ResponseEntity<String> removerDespesasPorIdCorrelacao(
			@PathVariable("idCorrelacao") String idCorrelacao,
			@RequestHeader("Authorization") String authorization) throws JsonMappingException, JsonProcessingException;
}
