package br.com.poupafacil.back.usecase.despesa.data.input;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DespesaDataInput {

	private String nomeDespesa;
	private Long grupo;
	private Long proprietarioDespesa;
	private String idCorrelacaoParcela;
	private BigDecimal valor;
	
	@Temporal(TemporalType.DATE)
	private LocalDate data;
	
	private Integer parcela;	
}
