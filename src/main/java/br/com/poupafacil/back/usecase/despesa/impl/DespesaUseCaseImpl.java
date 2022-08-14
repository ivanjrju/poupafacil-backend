package br.com.poupafacil.back.usecase.despesa.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.Transient;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import br.com.poupafacil.back.commons.enums.Periodo;
import br.com.poupafacil.back.gateway.despesa.DespesaRepository;
import br.com.poupafacil.back.gateway.despesa.facade.BuscarDespesasFacade;
import br.com.poupafacil.back.gateway.despesa.model.ConsolidadoTagModel;
import br.com.poupafacil.back.gateway.despesa.model.DespesaModel;
import br.com.poupafacil.back.gateway.pessoa.model.PessoaModel;
import br.com.poupafacil.back.usecase.despesa.DespesaUseCase;
import br.com.poupafacil.back.usecase.despesa.data.input.DespesaDataInput;
import br.com.poupafacil.back.usecase.despesa.data.output.ConsolidadoMesDespesaDataOutput;
import br.com.poupafacil.back.usecase.despesa.data.output.ConsolidadoTagOutput;
import br.com.poupafacil.back.usecase.despesa.data.output.DespesaDataOutput;
import br.com.poupafacil.back.usecase.despesa.mapper.DespesaUseCaseMapper;
import br.com.poupafacil.back.usecase.grupo.GrupoUseCase;
import br.com.poupafacil.back.usecase.grupo.data.output.GrupoDataOutput;
import br.com.poupafacil.back.usecase.grupo.mapper.GrupoUseCaseMapper;
import br.com.poupafacil.back.usecase.pessoa.data.output.PessoaDataOutput;
import br.com.poupafacil.back.usecase.pessoa.mapper.PessoaUseCaseMapper;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
@Transactional
public class DespesaUseCaseImpl implements DespesaUseCase {

	private DespesaUseCaseMapper despesaUseCaseMapper;
	private DespesaRepository despesaRepository;
	
	private GrupoUseCase grupoUseCase;
	private GrupoUseCaseMapper grupoUseCaseMapper;
	
	private PessoaUseCaseMapper pessoaUseCaseMapper;
	
	private BuscarDespesasFacade buscarDespesasFacade;
	
	
	@Override
	@Transient
	public List<DespesaDataOutput> criarDespesaUseCase(DespesaDataInput despesaDataInput, PessoaDataOutput pessoaDataOutput) throws CloneNotSupportedException {
		
		DespesaModel despesaModel = despesaUseCaseMapper.fromDespesaModel(despesaDataInput);
		
		PessoaModel pessoaModel = pessoaUseCaseMapper.fromPessoaModel(pessoaDataOutput);
		despesaModel.setProprietarioDespesa(pessoaModel);
		
		int quantidadePessoa = 1;
		
		if(Objects.nonNull(despesaDataInput.getGrupo())){
			List<GrupoDataOutput> gruposDataOutput = grupoUseCase.buscarGruposPorPessoaUseCase(pessoaModel.getIdPessoa());
			
			GrupoDataOutput grupoDataOutput = gruposDataOutput.stream()
				.filter(grupoDataOut -> grupoDataOut.getIdGrupo() == despesaDataInput.getGrupo())
				.findFirst()
				.orElseThrow();
			
			despesaModel.setGrupo(grupoUseCaseMapper.toGrupoDataOutput(grupoDataOutput));
			quantidadePessoa = grupoDataOutput.getPessoas().size();
		}
		
		if(Objects.isNull(despesaDataInput.getTag()))
			despesaModel.setTag("Outros");
		
		String idCorrelacaoParcela = UUID.randomUUID().toString();
		despesaModel.setIdCorrelacaoParcela(idCorrelacaoParcela);
		
		List<DespesaModel> despesasModelParcelado = efetuarParcelamento(despesaDataInput, despesaModel, quantidadePessoa);
	
		return despesaUseCaseMapper.toDespesasModel(despesaRepository.saveAllAndFlush(despesasModelParcelado));
 	}

	private List<DespesaModel> efetuarParcelamento(DespesaDataInput despesaDataInput, DespesaModel despesaModel, int quantidadePessoa)
			throws CloneNotSupportedException {
	
		List<DespesaModel> despesasModelParcelado = new ArrayList<DespesaModel>();
		
		int acrescentarMes = 0;
		
		for (int parcela = despesaDataInput.getParcela(); parcela >= 1; parcela--) {
			
			BigDecimal valorParcelado = despesaDataInput.getValor().divide(new BigDecimal(despesaDataInput.getParcela()), 2, RoundingMode.HALF_UP);
			BigDecimal valorDividido = valorParcelado.divide(new BigDecimal(quantidadePessoa));
			despesaModel.setValorDespesa(valorDividido);
			despesaModel.setParcela(parcela);
		
			despesaModel.setData(despesaDataInput.getData().plusMonths(acrescentarMes));
			acrescentarMes++;
			
			despesasModelParcelado.add(despesaModel.clone());
		}
		
		return despesasModelParcelado;
	}

	@Override
	public List<ConsolidadoMesDespesaDataOutput> buscarDespesasPorGrupoUseCases(Long idGrupo, Periodo periodo, PessoaDataOutput pessoaDataOutput) {

		grupoUseCase.buscarGrupoUseCase(idGrupo).getPessoas().stream()
			.filter(pessoa -> pessoa.getIdPessoa() == pessoaDataOutput.getIdPessoa())
			.findFirst()
			.orElseThrow();
		
		List<DespesaModel> despesasModel = buscarDespesasFacade.porGrupo(idGrupo, periodo);
		Set<String> anoMes = buscarMesesNaDespesas(despesasModel);
		return consolidarDespesasPorMes(despesasModel, anoMes);
	}
	
	@Override
	public List<ConsolidadoMesDespesaDataOutput> buscarDespesasPorPessoaUseCases(Long idPessoa, Periodo periodo, Long idGrupo) {
		
		List<DespesaModel> despesasModel = buscarDespesasFacade.porPessoa(idPessoa, periodo, idGrupo);
		Set<String> anoMes = buscarMesesNaDespesas(despesasModel);
		return consolidarDespesasPorMes(despesasModel, anoMes);
	}
	
	@Override
	public List<ConsolidadoMesDespesaDataOutput> buscarDespesasParaEstimativasUseCases(Long idPessoa) {
		
		List<DespesaModel> despesasModel = buscarDespesasFacade.porEstimativas(idPessoa);
		Set<String> anoMes = buscarMesesNaDespesas(despesasModel);
		return consolidarDespesasPorMes(despesasModel, anoMes);
	}	

	private List<ConsolidadoMesDespesaDataOutput> consolidarDespesasPorMes(List<DespesaModel> despesasModel, Set<String> anoMes) {
	
		List<ConsolidadoMesDespesaDataOutput> consolidadosMesesDespesasDataOutput = new ArrayList<ConsolidadoMesDespesaDataOutput>();
			
		for(String dataAnoMes : anoMes) {
			
			List<DespesaModel> despesasAnoMesFiltradas = despesasModel.stream().filter(compra ->{
				return compra.getData().toString().substring(0, 7).equals(dataAnoMes);
			})
			.collect(Collectors.toList());
			
			Optional<BigDecimal> totalDespesasMes = despesasAnoMesFiltradas.stream().map(DespesaModel::getValorDespesa).reduce(BigDecimal::add);
			
			consolidadosMesesDespesasDataOutput.add(
					ConsolidadoMesDespesaDataOutput.builder()
					.data(dataAnoMes)
					.totalDespesasMes(totalDespesasMes.get())
					.despesas(despesaUseCaseMapper.toDespesasModel(despesasAnoMesFiltradas))
					.build());	
		}
		
		return consolidadosMesesDespesasDataOutput;
	}

	private Set<String> buscarMesesNaDespesas(List<DespesaModel> despesasModel) {
		
		Set<String> anoMes = new LinkedHashSet<String>();
		for(DespesaModel despesaModel : despesasModel) {
			anoMes.add(despesaModel.getData().toString().substring(0, 7));	
		}
		
		return anoMes;
	}

	@Override
	public List<ConsolidadoTagOutput> buscarTagsPorDespesasUseCases(Long idPessoa) {
		
		List<ConsolidadoTagModel> consolidadoTagModel = despesaRepository.findByValorGroupByTag(idPessoa);
		return despesaUseCaseMapper.toConsolidadoTagOutput(consolidadoTagModel);
	}
	
	@Override
	public void removerDespesasPorIdCorrelacaoUseCase(String idCorrelacao, PessoaDataOutput pessoaDataOutput) {
		
		List<DespesaModel> despesasModel = despesaRepository.findAllByIdCorrelacaoParcela(idCorrelacao);
		
		despesasModel.stream()
			.filter(despesaModel -> despesaModel.getProprietarioDespesa().getIdPessoa() == pessoaDataOutput.getIdPessoa())
			.findFirst()
			.orElseThrow();
		
		despesaRepository.deleteAllByIdCorrelacaoParcela(idCorrelacao);
	}
}