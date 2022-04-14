package br.com.poupafacil.back.gateway.despesa.facade;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.poupafacil.back.commons.enums.Periodo;
import br.com.poupafacil.back.gateway.despesa.DespesaRepository;
import br.com.poupafacil.back.gateway.despesa.model.DespesaModel;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BuscarDespesasFacade {

	private DespesaRepository despesaRepository;
	
	public List<DespesaModel> porGrupo(Long idGrupo, Periodo periodo){
		
		return despesaRepository.findByGrupoIdGrupoAndDataBetweenOrderByData(
				idGrupo,
				YearMonth.now().atDay(1),
				YearMonth.now().atEndOfMonth().plusMonths(periodo.getTempo() - 1));
	}
	
	public List<DespesaModel> porPessoa(Long idPessoa, Periodo periodo, Long idGrupo){
		
		if(Objects.isNull(idGrupo))
			return despesaRepository.findByProprietarioDespesaIdPessoaAndDataBetweenOrderByData(
					idPessoa,
					YearMonth.now().atDay(1),
					YearMonth.now().atEndOfMonth().plusMonths(periodo.getTempo() - 1));
		else {
			return despesaRepository.findByGrupoIdGrupoAndProprietarioDespesaIdPessoaAndDataBetweenOrderByData(
					idGrupo,
					idPessoa,
					YearMonth.now().atDay(1),
					YearMonth.now().atEndOfMonth().plusMonths(periodo.getTempo() - 1));
		}
	}
	
	public List<DespesaModel> porEstimativas(Long idPessoa){
		
		LocalDate to = YearMonth.now().atDay(1).minusMonths(1);
		LocalDate from = YearMonth.now().atEndOfMonth().plusMonths(1);
		
		return despesaRepository.findByProprietarioDespesaIdPessoaAndDataBetweenOrderByData(
					idPessoa,
					to,
					from);
	}
}
