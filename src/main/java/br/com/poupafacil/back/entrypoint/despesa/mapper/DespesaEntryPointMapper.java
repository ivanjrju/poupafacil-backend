package br.com.poupafacil.back.entrypoint.despesa.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.poupafacil.back.entrypoint.despesa.data.request.DespesaRequest;
import br.com.poupafacil.back.entrypoint.despesa.data.response.ConsolidadoEstimativaDespesaResponse;
import br.com.poupafacil.back.entrypoint.despesa.data.response.ConsolidadoMesDespesaResponse;
import br.com.poupafacil.back.entrypoint.despesa.data.response.DespesaResponse;
import br.com.poupafacil.back.entrypoint.grupo.mapper.GrupoEntryPointMapper;
import br.com.poupafacil.back.usecase.despesa.data.input.DespesaDataInput;
import br.com.poupafacil.back.usecase.despesa.data.output.ConsolidadoMesDespesaDataOutput;
import br.com.poupafacil.back.usecase.despesa.data.output.DespesaDataOutput;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DespesaEntryPointMapper {

	private GrupoEntryPointMapper grupoEntryPointMapper;
	
	public DespesaDataInput fromDespesaUseCaseInput(DespesaRequest despesaRequest) {
		
		return DespesaDataInput.builder()
				.nomeDespesa(despesaRequest.getNomeDespesa())
				.proprietarioDespesa(despesaRequest.getProprietarioDespesa())
				.grupo(despesaRequest.getIdGrupo())
				.valor(despesaRequest.getValor())
				.data(despesaRequest.getData())
				.parcela(despesaRequest.getParcela())
				.tag(despesaRequest.getTag())
				.build();		
	}
	
	public DespesaResponse toDespesaUseCaseOutput(DespesaDataOutput despesaDataOutput) {
		
		return DespesaResponse.builder()
			.idDespesa(despesaDataOutput.getIdDespesa())
			.nomeDespesa(despesaDataOutput.getNomeDespesa())
			.grupo(grupoEntryPointMapper.toGrupoDataOutput(despesaDataOutput.getGrupo()))
			.idProprietarioDespesa(Objects.isNull(despesaDataOutput.getProprietarioDespesa()) ? null : despesaDataOutput.getProprietarioDespesa().getIdPessoa())
			.idCorrelacaoParcela(despesaDataOutput.getIdCorrelacaoParcela())
			.valorDespesa(despesaDataOutput.getValorDespesa())
			.valorTotal(despesaDataOutput.getValorTotal())
			.data(despesaDataOutput.getData())
			.parcela(despesaDataOutput.getParcela())
			.parcelaTotal(despesaDataOutput.getParcelaTotal())
			.tag(despesaDataOutput.getTag())
			.build();
	}

	public List<DespesaResponse> toDespesasUseCaseOutput(List<DespesaDataOutput> despesasDataOutput) {
		
		return despesasDataOutput.stream().map(despesaDataOutput -> toDespesaUseCaseOutput(despesaDataOutput)).collect(Collectors.toList());
	}
	
	public ConsolidadoMesDespesaResponse toConsolidadoMesDespesaDataOutput(ConsolidadoMesDespesaDataOutput consolidadoMesDespesaDataOutput) {
		
		return ConsolidadoMesDespesaResponse.builder()
				.data(consolidadoMesDespesaDataOutput.getData())
				.totalDespesasMes(consolidadoMesDespesaDataOutput.getTotalDespesasMes())
				.despesas(toDespesasUseCaseOutput(consolidadoMesDespesaDataOutput.getDespesas()))
				.build();
	}

	public List<ConsolidadoMesDespesaResponse> toConsolidadoMesesDespesasDataOutput(
			List<ConsolidadoMesDespesaDataOutput> consolidadoMesesDespesasDataOutput) {
		
		return consolidadoMesesDespesasDataOutput.stream().map(consolidado -> toConsolidadoMesDespesaDataOutput(consolidado)).collect(Collectors.toList());
	}
	
	public ConsolidadoEstimativaDespesaResponse toConsolidadoEstimativaDespesaResponse(ConsolidadoMesDespesaDataOutput consolidadoMesDespesaDataOutput) {
		
		return ConsolidadoEstimativaDespesaResponse.builder()
				.data(consolidadoMesDespesaDataOutput.getData())
				.totalDespesasMes(consolidadoMesDespesaDataOutput.getTotalDespesasMes())
				.build();
	}

	public List<ConsolidadoEstimativaDespesaResponse> toConsolidadoEstimativasDespesaResponse(
			List<ConsolidadoMesDespesaDataOutput> consolidadoMesesDespesasDataOutput) {
		
		return consolidadoMesesDespesasDataOutput.stream().map(consolidado -> toConsolidadoEstimativaDespesaResponse(consolidado)).collect(Collectors.toList());
	}
	
}
