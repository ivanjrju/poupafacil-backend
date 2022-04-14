package br.com.poupafacil.back.usecase.grupo;

import br.com.poupafacil.back.usecase.grupo.data.input.GrupoDataInput;
import br.com.poupafacil.back.usecase.grupo.data.output.GrupoDataOutput;

public interface GrupoUseCase {

	public GrupoDataOutput criarGrupoUseCase (GrupoDataInput grupoDataInput);
	public GrupoDataOutput buscarGrupoUseCase(Long idGrupo);
}
