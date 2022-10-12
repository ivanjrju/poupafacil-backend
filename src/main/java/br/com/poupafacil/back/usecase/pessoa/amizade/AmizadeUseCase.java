package br.com.poupafacil.back.usecase.pessoa.amizade;

import java.util.List;

import br.com.poupafacil.back.usecase.pessoa.amizade.data.input.AmizadeDataInput;
import br.com.poupafacil.back.usecase.pessoa.amizade.data.output.AmizadeDataOuput;

public interface AmizadeUseCase {

	public void solicitarAmizade(AmizadeDataInput amizadeDataInput);
	public List<AmizadeDataOuput> listarPedidoAmizade(String email);
	public List<AmizadeDataOuput> aceitarPedidoAmizade(String pessoa, String emailSolicitante);
}
