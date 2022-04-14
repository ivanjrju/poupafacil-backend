package br.com.poupafacil.back.gateway.despesa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.poupafacil.back.gateway.despesa.model.DespesaModel;

public interface DespesaRepository extends JpaRepository<DespesaModel, Long>{

	List<DespesaModel> findByGrupoIdGrupo(Long idGrupo);
	List<DespesaModel> findByGrupoIdGrupoAndDataBetweenOrderByData(Long idGrupo, LocalDate to, LocalDate from);
	List<DespesaModel> findByProprietarioDespesaIdPessoa(Long idPessoa);
	List<DespesaModel> findByProprietarioDespesaIdPessoaAndDataBetweenOrderByData(Long idPessoa, LocalDate to, LocalDate from);	
	List<DespesaModel> findByGrupoIdGrupoAndProprietarioDespesaIdPessoaAndDataBetweenOrderByData(Long idGrupo, Long idPessoa, LocalDate to, LocalDate from);
	void deleteAllByIdCorrelacaoParcela(String idCorrelacao);
	
}
