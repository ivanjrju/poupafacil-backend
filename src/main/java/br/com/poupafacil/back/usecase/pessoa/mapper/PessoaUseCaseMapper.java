package br.com.poupafacil.back.usecase.pessoa.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.poupafacil.back.gateway.pessoa.model.PessoaModel;
import br.com.poupafacil.back.usecase.pessoa.data.input.PessoaDataInput;
import br.com.poupafacil.back.usecase.pessoa.data.output.PessoaDataOutput;

@Component
public class PessoaUseCaseMapper {

	public PessoaModel fromPessoaModel(PessoaDataInput pessoaDataInput) {
		
		return PessoaModel.builder()
				.email(pessoaDataInput.getEmail())
				.nome(pessoaDataInput.getNome())
				.renda(pessoaDataInput.getRenda())
				.build();
	}
	
	public List<PessoaModel> fromPessoasModel(List<PessoaDataInput> pessoasDataInput) {
		
		return pessoasDataInput.stream()
			.map(pessoaDataInput -> fromPessoaModel(pessoaDataInput))
			.collect(Collectors.toList());
	}
	
	public PessoaModel fromPessoaModel(PessoaDataOutput pessoaDataOutput) {
		
		return PessoaModel.builder()
				.idPessoa(pessoaDataOutput.getIdPessoa())
				.email(pessoaDataOutput.getEmail())
				.nome(pessoaDataOutput.getNome())
				.renda(pessoaDataOutput.getRenda())
				.build();
	}
	
	public List<PessoaModel> toPessoaDataOutput(List<PessoaDataOutput> pessoasDataOutput) {
		
		return pessoasDataOutput.stream()
				.map(pessoaDataOutput -> fromPessoaModel(pessoaDataOutput))
				.collect(Collectors.toList());
	}
	
	public PessoaDataOutput toPessoaModel(PessoaModel pessoaModel) {
		
		return PessoaDataOutput.builder()
				.idPessoa(pessoaModel.getIdPessoa())
				.email(pessoaModel.getEmail())
				.nome(pessoaModel.getNome())
				.renda(pessoaModel.getRenda())
				.build();
	}
	
	public List<PessoaDataOutput> toPessoasModel(List<PessoaModel> pessoasModel) {
		
		return pessoasModel.stream().map(pessoaModel -> toPessoaModel(pessoaModel)).collect(Collectors.toList());
	}

	
}
