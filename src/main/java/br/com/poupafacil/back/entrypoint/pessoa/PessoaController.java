package br.com.poupafacil.back.entrypoint.pessoa;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.poupafacil.back.entrypoint.pessoa.data.request.PessoaRequest;
import br.com.poupafacil.back.entrypoint.pessoa.data.response.PessoaResponse;

@RestController
@RequestMapping("/api/pessoas")
public interface PessoaController {

	@CrossOrigin
	@PostMapping
	public ResponseEntity<PessoaResponse> criarPessoa(
			@RequestBody @Valid PessoaRequest pessoaRequest);
	
	@CrossOrigin
	@GetMapping
	public ResponseEntity<PessoaResponse> buscarPessoa(
			@RequestHeader("Authorization") String authorization) throws Exception;
}
