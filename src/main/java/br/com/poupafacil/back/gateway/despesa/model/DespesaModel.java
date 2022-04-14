package br.com.poupafacil.back.gateway.despesa.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.poupafacil.back.gateway.grupo.model.GrupoModel;
import br.com.poupafacil.back.gateway.pessoa.model.PessoaModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "despesa")
public class DespesaModel implements Cloneable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idDespesa;
	
	@ManyToOne
	private PessoaModel proprietarioDespesa;
	
	@ManyToOne
	private GrupoModel grupo;
	
	private String idCorrelacaoParcela;
	private BigDecimal valorDespesa;
	private BigDecimal valorTotal;
	private LocalDate data;
	private Integer parcela;
	private Integer parcelaTotal;
	
	@Override
    public DespesaModel clone() throws CloneNotSupportedException {
        return (DespesaModel) super.clone();
    }
}
