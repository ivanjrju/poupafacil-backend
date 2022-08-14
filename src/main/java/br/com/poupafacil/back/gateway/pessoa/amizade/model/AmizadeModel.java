package br.com.poupafacil.back.gateway.pessoa.amizade.model;

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
@Table(name = "amizade")
public class AmizadeModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idAmizade;
	
	private String emailSolicitado;
	private String emailSolicitante;
}
