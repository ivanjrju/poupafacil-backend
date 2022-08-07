package br.com.poupafacil.back.entrypoint.pessoa.amizade;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.poupafacil.back.entrypoint.pessoa.amizade.data.request.AmizadeRequest;
import br.com.poupafacil.back.entrypoint.pessoa.amizade.data.response.AmizadeResponse;

@RestController
@RequestMapping("/api/pessoas/amizade")
public interface AmizadeController {

	@CrossOrigin
	@PostMapping
	public ResponseEntity<List<AmizadeResponse>> solicitarAmizade(
			@RequestBody @Valid AmizadeRequest amizadeRequest);
	
}
