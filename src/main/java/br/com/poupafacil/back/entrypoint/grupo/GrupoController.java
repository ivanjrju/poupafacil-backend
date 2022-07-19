package br.com.poupafacil.back.entrypoint.grupo;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.poupafacil.back.entrypoint.grupo.data.request.GrupoRequest;
import br.com.poupafacil.back.entrypoint.grupo.data.response.GrupoPorPessoaResponse;
import br.com.poupafacil.back.entrypoint.grupo.data.response.GrupoResponse;

@RestController
@RequestMapping("/api/grupos")
public interface GrupoController {

	@CrossOrigin
	@PostMapping
	public ResponseEntity<GrupoResponse> criarGrupo(
			@RequestBody @Valid GrupoRequest grupoRequest,
			@RequestHeader("Authorization") String authorization) throws Exception;
	
	@CrossOrigin
	@GetMapping("/{idGrupo}")
	public ResponseEntity<GrupoResponse> buscarGrupo(
			@PathVariable("idGrupo") Long idGrupo,
			@RequestHeader("Authorization") String authorization) throws Exception;
	
	@CrossOrigin
	@GetMapping("/pessoa/{idPessoa}")
	public ResponseEntity<List<GrupoPorPessoaResponse>> buscarGruposPorPessoa(
			@PathVariable("idPessoa") Long idPessoa,
			@RequestHeader("Authorization") String authorization) throws Exception;
}
