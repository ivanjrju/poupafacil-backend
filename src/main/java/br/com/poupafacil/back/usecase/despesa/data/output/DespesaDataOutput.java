package br.com.poupafacil.back.usecase.despesa.data.output;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.poupafacil.back.usecase.grupo.data.output.GrupoDataOutput;
import br.com.poupafacil.back.usecase.pessoa.data.output.PessoaDataOutput;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DespesaDataOutput {

	private Long idDespesa;
	private String nomeDespesa;
	private GrupoDataOutput grupo;
	private PessoaDataOutput proprietarioDespesa;
	private String idCorrelacaoParcela;
	private BigDecimal valorDespesa;
	private BigDecimal valorTotal;
	
	@Temporal(TemporalType.DATE)
	private LocalDate data;
	
	private Integer parcela;
	private Integer parcelaTotal;
}
