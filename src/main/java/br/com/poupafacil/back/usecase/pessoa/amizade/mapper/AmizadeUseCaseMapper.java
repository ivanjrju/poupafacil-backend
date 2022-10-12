package br.com.poupafacil.back.usecase.pessoa.amizade.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.poupafacil.back.gateway.pessoa.amizade.model.PedidoAmizadeModel;
import br.com.poupafacil.back.usecase.pessoa.amizade.data.input.AmizadeDataInput;
import br.com.poupafacil.back.usecase.pessoa.amizade.data.output.AmizadeDataOuput;

@Component
public class AmizadeUseCaseMapper {

	public PedidoAmizadeModel fromAmizadeModel(AmizadeDataInput amizadeDataInput) {
		
		return PedidoAmizadeModel.builder()
				.emailSolicitante(amizadeDataInput.getEmailSolicitante())
				.emailSolicitado(amizadeDataInput.getEmailSolicitado())
				.build();
	}
	
	public AmizadeDataOuput toAmizadeModel(PedidoAmizadeModel pedidoAmizadeModel) {
		
		return AmizadeDataOuput.builder()
				.email(pedidoAmizadeModel.getEmailSolicitante())
				.build();
	}

	public List<AmizadeDataOuput> toAmizadesModel(List<PedidoAmizadeModel> amizadesModel) {
		
		return amizadesModel.stream().map(amizadeModel -> toAmizadeModel(amizadeModel)).collect(Collectors.toList());
	}
}
