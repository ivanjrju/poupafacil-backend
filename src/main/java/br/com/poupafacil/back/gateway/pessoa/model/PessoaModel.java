package br.com.poupafacil.back.gateway.pessoa.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pessoa")
public class PessoaModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPessoa;
	
	@Column(unique=true)
	private String email;
	
	private String nome;
	private BigDecimal renda;
}
