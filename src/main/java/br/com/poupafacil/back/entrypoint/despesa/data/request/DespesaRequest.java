package br.com.poupafacil.back.entrypoint.despesa.data.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class DespesaRequest {

	@NotNull(message = "'nomeDespesa' é obrigatório")
	private String nomeDespesa;
	
	private Long idGrupo;
	
	@NotNull(message = "'proprietarioDespesa' é obrigatório")
	private Long proprietarioDespesa;
	
	@NotNull(message = "'valor' é obrigatório")
	private BigDecimal valor;
	
	@NotNull(message = "'parcela' é obrigatório")
	private Integer parcela;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private LocalDate data;
	
	
}
