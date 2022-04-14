package br.com.poupafacil.back.usecase.pessoa.impl;

import org.springframework.stereotype.Component;

import br.com.poupafacil.back.gateway.pessoa.PessoaRepository;
import br.com.poupafacil.back.gateway.pessoa.model.PessoaModel;
import br.com.poupafacil.back.usecase.pessoa.PessoaUseCase;
import br.com.poupafacil.back.usecase.pessoa.data.input.PessoaDataInput;
import br.com.poupafacil.back.usecase.pessoa.data.output.PessoaDataOutput;
import br.com.poupafacil.back.usecase.pessoa.mapper.PessoaUseCaseMapper;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PessoaUseCaseImpl implements PessoaUseCase {

	private PessoaUseCaseMapper pessoaUseCaseMapper;
	private PessoaRepository pessoaRepository;
	
	@Override
	public PessoaDataOutput criarPessoaUseCase(PessoaDataInput pessoaDataInput) {
		
		PessoaModel pessoaModel = pessoaUseCaseMapper.fromPessoaModel(pessoaDataInput);
		return pessoaUseCaseMapper.toPessoaModel(pessoaRepository.save(pessoaModel));
	}

	@Override
	public PessoaDataOutput buscarPessoaUseCase(Long idPessoa) {
		
		PessoaModel pessoaModel = pessoaRepository.getById(idPessoa);
		return pessoaUseCaseMapper.toPessoaModel(pessoaModel);
	}
}
