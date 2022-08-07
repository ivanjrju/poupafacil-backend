package br.com.poupafacil.back.usecase.pessoa.amizade.data.input;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AmizadeDataInput {

	private String emailSolicitado;
	private String emailSolicitante;
	
}