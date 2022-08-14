package br.com.poupafacil.back.usecase.pessoa.amizade.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.poupafacil.back.gateway.pessoa.amizade.AmizadeRepository;
import br.com.poupafacil.back.gateway.pessoa.amizade.model.AmizadeModel;
import br.com.poupafacil.back.usecase.pessoa.amizade.AmizadeUseCase;
import br.com.poupafacil.back.usecase.pessoa.amizade.data.input.AmizadeDataInput;
import br.com.poupafacil.back.usecase.pessoa.amizade.data.output.AmizadeDataOuput;
import br.com.poupafacil.back.usecase.pessoa.amizade.mapper.AmizadeUseCaseMapper;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AmizadeUseCaseImpl implements AmizadeUseCase {

	private AmizadeUseCaseMapper amizadeUseCaseMapper;
	private AmizadeRepository amizadeRepository;
	
	@Override
	public void solicitarAmizade(AmizadeDataInput amizadeDataInput) {
		
		amizadeRepository.save(amizadeUseCaseMapper.fromAmizadeModel(amizadeDataInput));
	}

	@Override
	public List<AmizadeDataOuput> listarPedidoAmizade(String email) {
		
		List<AmizadeModel> amizadesModel = amizadeRepository.findAllByEmailSolicitado(email);
		return amizadeUseCaseMapper.toAmizadesModel(amizadesModel);
	}
}
