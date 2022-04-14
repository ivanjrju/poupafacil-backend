package br.com.poupafacil.back.usecase.pessoa;

import br.com.poupafacil.back.usecase.pessoa.data.input.PessoaDataInput;
import br.com.poupafacil.back.usecase.pessoa.data.output.PessoaDataOutput;

public interface PessoaUseCase {

	public PessoaDataOutput criarPessoaUseCase(PessoaDataInput pessoaDataInput);
	public PessoaDataOutput buscarPessoaUseCase(Long idPessoa);
	
}
