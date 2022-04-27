package br.com.poupafacil.back.usecase.despesa.data.output;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConsolidadoTagOutput {

	private BigDecimal total;
	private String tag;
}
