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
			@PathParam("periodo") Periodo periodo);

	@CrossOrigin
	@GetMapping("/pessoa/{idPessoa}")
	public ResponseEntity<List<ConsolidadoMesDespesaResponse>> buscarDespesasPorPessoa(
			@PathVariable("idPessoa") Long idPessoa,
			@PathParam("periodo") Periodo periodo,
			@PathParam("idGrupo") Long idGrupo);
	
	@CrossOrigin
	@GetMapping("/pessoa/{idPessoa}/estimativas")
	public ResponseEntity<List<ConsolidadoEstimativaDespesaResponse>> buscarDespesasParaEstimativas(
			@PathVariable("idPessoa") Long idPessoa);
	
	@CrossOrigin
	@GetMapping("/tags/pessoa/{idPessoa}")
	public ResponseEntity<List<ConsolidadoTagResponse>> buscarTagsPorDespesas(
			@PathVariable("idPessoa") Long idPessoa);
	
	@CrossOrigin
	@DeleteMapping("/idCorrelacao/{idCorrelacao}")
	public ResponseEntity<String> removerDespesasPorIdCorrelacao(
			@PathVariable("idCorrelacao") String idCorrelacao);
}
