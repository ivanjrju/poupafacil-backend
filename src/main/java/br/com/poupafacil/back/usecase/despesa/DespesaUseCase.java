package br.com.poupafacil.back.usecase.despesa;

import java.util.List;

import br.com.poupafacil.back.commons.enums.Periodo;
import br.com.poupafacil.back.usecase.despesa.data.input.DespesaDataInput;
import br.com.poupafacil.back.usecase.despesa.data.output.ConsolidadoMesDespesaDataOutput;
import br.com.poupafacil.back.usecase.despesa.data.output.DespesaDataOutput;

public interface DespesaUseCase {

	public List<DespesaDataOutput> criarDespesaUseCase(DespesaDataInput despesaDataInput) throws CloneNotSupportedException;
	public List<ConsolidadoMesDespesaDataOutput> buscarDespesasPorGrupoUseCases(Long idGrupo, Periodo periodo);
	public List<ConsolidadoMesDespesaDataOutput> buscarDespesasPorPessoaUseCases(Long idPessoa, Periodo periodo, Long idGrupo);
	public List<ConsolidadoMesDespesaDataOutput> buscarDespesasParaEstimativasUseCases(Long idPessoa);
	public void removerDespesasPorIdCorrelacaoUseCase(String idCorrelacao);
}
