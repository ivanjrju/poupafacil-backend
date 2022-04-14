package br.com.poupafacil.back.entrypoint.despesa.data.response;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConsolidadoEstimativaDespesaResponse {

	private String data;
	private BigDecimal totalDespesasMes;
}
