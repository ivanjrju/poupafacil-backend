package br.com.poupafacil.back.gateway.grupo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
@Table(name = "grupo")
public class GrupoModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idGrupo;
	
	private String nome;
	
	@ManyToMany
	private List<PessoaModel> pessoas;
}
