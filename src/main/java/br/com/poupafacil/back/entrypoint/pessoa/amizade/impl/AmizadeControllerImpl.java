package br.com.poupafacil.back.entrypoint.pessoa.amizade.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.poupafacil.back.entrypoint.pessoa.amizade.AmizadeController;
import br.com.poupafacil.back.entrypoint.pessoa.amizade.data.request.AmizadeRequest;
import br.com.poupafacil.back.entrypoint.pessoa.amizade.data.response.AmizadeResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor 
public class AmizadeControllerImpl implements AmizadeController {@Override
	
	public ResponseEntity<List<AmizadeResponse>> solicitarAmizade(@Valid AmizadeRequest amizadeRequest,
			String authorization) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<AmizadeResponse>> listarPedidoAmizade(@Valid AmizadeRequest amizadeRequest,
			String authorization) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<AmizadeResponse>> aceitarPedidoAmizade(@Valid AmizadeRequest amizadeRequest,
			String authorization) {
		// TODO Auto-generated method stub
		return null;
	}



}
