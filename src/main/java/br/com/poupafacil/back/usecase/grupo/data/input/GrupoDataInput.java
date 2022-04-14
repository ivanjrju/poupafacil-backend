package br.com.poupafacil.back.usecase.grupo.data.input;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GrupoDataInput {

	private String nome;
	private List<Long> participantes;
}
