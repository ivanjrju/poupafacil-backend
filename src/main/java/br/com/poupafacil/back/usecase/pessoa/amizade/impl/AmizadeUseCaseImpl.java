package br.com.poupafacil.back.usecase.pessoa.amizade.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.poupafacil.back.gateway.pessoa.amizade.PedidoAmizadeRepository;
import br.com.poupafacil.back.gateway.pessoa.amizade.model.PedidoAmizadeModel;
import br.com.poupafacil.back.usecase.pessoa.amizade.AmizadeUseCase;
import br.com.poupafacil.back.usecase.pessoa.amizade.data.input.AmizadeDataInput;
import br.com.poupafacil.back.usecase.pessoa.amizade.data.output.AmizadeDataOuput;
import br.com.poupafacil.back.usecase.pessoa.amizade.mapper.AmizadeUseCaseMapper;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AmizadeUseCaseImpl implements AmizadeUseCase {

	private AmizadeUseCaseMapper amizadeUseCaseMapper;
	private PedidoAmizadeRepository pedidoAmizadeRepository;
	
	@Override
	public void solicitarAmizade(AmizadeDataInput amizadeDataInput) {
		
		pedidoAmizadeRepository.save(amizadeUseCaseMapper.fromAmizadeModel(amizadeDataInput));
	}

	@Override
	public List<AmizadeDataOuput> listarPedidoAmizade(String email) {
		
		List<PedidoAmizadeModel> amizadesModel = pedidoAmizadeRepository.findAllByEmailSolicitado(email);
		return amizadeUseCaseMapper.toAmizadesModel(amizadesModel);
	}

	@Override
	public List<AmizadeDataOuput> aceitarPedidoAmizade(String pessoa, String emailSolicitante) {
		
		pedidoAmizadeRepository.findAllByEmailSolicitadoAndEmailSolicitante(pessoa, emailSolicitante);
		
		
		return null;
	}
}
