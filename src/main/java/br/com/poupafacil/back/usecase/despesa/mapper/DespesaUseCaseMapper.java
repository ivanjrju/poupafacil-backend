package br.com.poupafacil.back.usecase.despesa.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.poupafacil.back.gateway.despesa.model.DespesaModel;
import br.com.poupafacil.back.usecase.despesa.data.input.DespesaDataInput;
import br.com.poupafacil.back.usecase.despesa.data.output.DespesaDataOutput;
import br.com.poupafacil.back.usecase.grupo.mapper.GrupoUseCaseMapper;
import br.com.poupafacil.back.usecase.pessoa.mapper.PessoaUseCaseMapper;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DespesaUseCaseMapper {
	
	private GrupoUseCaseMapper grupoUseCaseMapper;
	private PessoaUseCaseMapper pessoaUseCaseMapper;

	public DespesaModel fromDespesaModel(DespesaDataInput despesaDataInput) {
		
		return DespesaModel.builder()
			.idCorrelacaoParcela(despesaDataInput.getIdCorrelacaoParcela())
			.valorDespesa(despesaDataInput.getValor())
			.valorTotal(despesaDataInput.getValor())
			.data(despesaDataInput.getData())
			.parcela(despesaDataInput.getParcela())
			.parcelaTotal(despesaDataInput.getParcela())
			.build();
	}
	
	public DespesaDataOutput toDespesaModel(DespesaModel despesaModel) {
		
		return DespesaDataOutput.builder()
			.idDespesa(despesaModel.getIdDespesa())
			.grupo(grupoUseCaseMapper.toGrupoModel(despesaModel.getGrupo()))
			.proprietarioDespesa(pessoaUseCaseMapper.toPessoaModel(despesaModel.getProprietarioDespesa()))
			.idCorrelacaoParcela(despesaModel.getIdCorrelacaoParcela())
			.valorDespesa(despesaModel.getValorDespesa())
			.valorTotal(despesaModel.getValorTotal())
			.data(despesaModel.getData())
			.parcela(despesaModel.getParcela())
			.parcelaTotal(despesaModel.getParcelaTotal())
			.build();
	}

	public List<DespesaDataOutput> toDespesasModel(List<DespesaModel> despesasModel) {
		
		return despesasModel.stream().map(despesaModel -> toDespesaModel(despesaModel)).collect(Collectors.toList());
	}
	
}
