package br.com.poupafacil.back.entrypoint.pessoa.amizade.mapper;

import org.springframework.stereotype.Component;

import br.com.poupafacil.back.entrypoint.pessoa.amizade.data.request.AmizadeRequest;
import br.com.poupafacil.back.usecase.pessoa.amizade.data.input.AmizadeDataInput;
import br.com.poupafacil.back.usecase.pessoa.data.output.PessoaDataOutput;

@Component
public class AmizadeEntryPointMapper {

	public AmizadeDataInput fromAmizadeDataInput(AmizadeRequest amizadeRequest, PessoaDataOutput pessoaDataOutput) {
		
		return AmizadeDataInput.builder()
				.emailSolicitado(amizadeRequest.getEmailSolicitado())
				.emailSolicitante(pessoaDataOutput.getEmail())		
				.build();	
	}
	
}
