package br.com.poupafacil.back.entrypoint.despesa.data.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConsolidadoMesDespesaResponse {

	private String data;
	private BigDecimal totalDespesasMes;
	private List<DespesaResponse> despesas;
}
