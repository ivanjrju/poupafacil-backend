package br.com.poupafacil.back.entrypoint.despesa.data.response;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConsolidadoTagResponse {

	private BigDecimal total;
	private String tag;
}
