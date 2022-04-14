package br.com.poupafacil.back.usecase.despesa.data.output;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConsolidadoMesDespesaDataOutput {

	private String data;
	private BigDecimal totalDespesasMes;
	private List<DespesaDataOutput> despesas;
}

