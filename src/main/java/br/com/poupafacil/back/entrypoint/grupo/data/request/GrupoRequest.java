package br.com.poupafacil.back.entrypoint.grupo.data.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class GrupoRequest {

	@NotEmpty(message = "'nome' é obrigatório")
	private String nome;

	@NotEmpty(message = "'participantes' é obrigatório")
	private List<String> participantes;
}
