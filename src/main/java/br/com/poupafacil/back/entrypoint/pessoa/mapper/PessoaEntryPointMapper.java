package br.com.poupafacil.back.entrypoint.pessoa.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.poupafacil.back.entrypoint.pessoa.data.request.PessoaRequest;
import br.com.poupafacil.back.entrypoint.pessoa.data.response.PessoaResponse;
import br.com.poupafacil.back.usecase.pessoa.data.input.PessoaDataInput;
import br.com.poupafacil.back.usecase.pessoa.data.output.PessoaDataOutput;

@Component
public class PessoaEntryPointMapper {

	public PessoaDataInput fromPessoaDataInput(PessoaRequest pessoaRequest) {
		
		return PessoaDataInput.builder()
				.nome(pessoaRequest.getNome())
				.email(pessoaRequest.getEmail())
				.senha(pessoaRequest.getSenha())
				.renda(pessoaRequest.getRenda())
				.build();
	}

	public PessoaResponse toPessoaResponse(PessoaDataOutput pessoaDataOutput) {
		
		return PessoaResponse.builder()
				.idPessoa(pessoaDataOutput.getIdPessoa())
				.nome(pessoaDataOutput.getNome())
				.email(pessoaDataOutput.getEmail())
				.renda(pessoaDataOutput.getRenda())
				.build();
	}

	public List<PessoaResponse> toPessoasResponse(List<PessoaDataOutput> pessoasDataOutput) {
		
		return pessoasDataOutput.stream().map(pessoaDataOutput -> toPessoaResponse(pessoaDataOutput)).collect(Collectors.toList());
	}
}
