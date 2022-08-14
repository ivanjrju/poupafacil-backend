package br.com.poupafacil.back.usecase.pessoa.amizade.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.poupafacil.back.gateway.pessoa.amizade.model.AmizadeModel;
import br.com.poupafacil.back.usecase.pessoa.amizade.data.input.AmizadeDataInput;
import br.com.poupafacil.back.usecase.pessoa.amizade.data.output.AmizadeDataOuput;

@Component
public class AmizadeUseCaseMapper {

	public AmizadeModel fromAmizadeModel(AmizadeDataInput amizadeDataInput) {
		
		return AmizadeModel.builder()
				.emailSolicitante(amizadeDataInput.getEmailSolicitante())
				.emailSolicitado(amizadeDataInput.getEmailSolicitado())
				.build();
	}
	
	public AmizadeDataOuput toAmizadeModel(AmizadeModel amizadeModel) {
		
		return AmizadeDataOuput.builder()
				.email(amizadeModel.getEmailSolicitante())
				.build();
	}

	public List<AmizadeDataOuput> toAmizadesModel(List<AmizadeModel> amizadesModel) {
		
		return amizadesModel.stream().map(amizadeModel -> toAmizadeModel(amizadeModel)).collect(Collectors.toList());
	}
}
