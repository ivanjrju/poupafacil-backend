package br.com.poupafacil.back.entrypoint.pessoa.amizade;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.poupafacil.back.entrypoint.pessoa.amizade.data.request.AmizadeRequest;
import br.com.poupafacil.back.entrypoint.pessoa.amizade.data.response.AmizadeResponse;

@RestController
@RequestMapping("/api/pessoas/amizades")
public interface AmizadeController {

	@CrossOrigin
	@PostMapping
	public ResponseEntity<Void> solicitarAmizade(
			@RequestBody @Valid AmizadeRequest amizadeRequest,
			@RequestHeader("Authorization") String authorization);
	
	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<AmizadeResponse>> listarPedidoAmizade(
			@RequestHeader("Authorization") String authorization);
	
	@CrossOrigin
	@PatchMapping("/{email}/aceite")
	public ResponseEntity<List<AmizadeResponse>> aceitarPedidoAmizade(
			@PathVariable("email") String email,
			@RequestHeader("Authorization") String authorization);
	
}
