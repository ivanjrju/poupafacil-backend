package br.com.poupafacil.back.entrypoint.despesa.data.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.poupafacil.back.entrypoint.grupo.data.response.GrupoResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DespesaResponse {

	private Long idDespesa;
	private GrupoResponse grupo;
	private Long idProprietarioDespesa;
	private String idCorrelacaoParcela;
	private BigDecimal valorDespesa;
	private BigDecimal valorTotal;
	
	@Temporal(TemporalType.DATE)
	private LocalDate data;
	
	private Integer parcela;
	private Integer parcelaTotal;
}
