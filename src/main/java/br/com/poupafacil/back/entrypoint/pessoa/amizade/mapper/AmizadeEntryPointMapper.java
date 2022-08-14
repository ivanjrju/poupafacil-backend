package br.com.poupafacil.back.entrypoint.pessoa.amizade.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.poupafacil.back.entrypoint.pessoa.amizade.data.request.AmizadeRequest;
import br.com.poupafacil.back.entrypoint.pessoa.amizade.data.response.AmizadeResponse;
import br.com.poupafacil.back.usecase.pessoa.amizade.data.input.AmizadeDataInput;
import br.com.poupafacil.back.usecase.pessoa.amizade.data.output.AmizadeDataOuput;
import br.com.poupafacil.back.usecase.pessoa.data.output.PessoaDataOutput;

@Component
public class AmizadeEntryPointMapper {

	public AmizadeDataInput fromAmizadeDataInput(AmizadeRequest amizadeRequest, PessoaDataOutput pessoaDataOutput) {
		
		return AmizadeDataInput.builder()
				.emailSolicitado(amizadeRequest.getEmailSolicitado())
				.emailSolicitante(pessoaDataOutput.getEmail())		
				.build();	
	}
	
	public AmizadeResponse toAmizadeResponse(AmizadeDataOuput amizadeDataOuput) {
		
		return AmizadeResponse.builder()
				.email(amizadeDataOuput.getEmail())
				.build();
	}

	public List<AmizadeResponse> toAmizadesResponse(List<AmizadeDataOuput> amizadesDataOuput) {
		
		return amizadesDataOuput.stream().map(amizadeDataOuput -> toAmizadeResponse(amizadeDataOuput)).collect(Collectors.toList());
	}
}
