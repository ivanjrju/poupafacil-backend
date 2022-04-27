package br.com.poupafacil.back.gateway.despesa.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConsolidadoTagModel {

	private BigDecimal sum;
	private String tag;
	
	public ConsolidadoTagModel() {}
	
	public ConsolidadoTagModel(BigDecimal sum, String tag) {
		this.sum = sum;
		this.tag = tag;
	}
}
