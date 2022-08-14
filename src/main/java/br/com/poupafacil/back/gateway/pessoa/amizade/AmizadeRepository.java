package br.com.poupafacil.back.gateway.pessoa.amizade;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.poupafacil.back.gateway.pessoa.amizade.model.AmizadeModel;

public interface AmizadeRepository extends JpaRepository<AmizadeModel, Long>{

	List<AmizadeModel> findAllByEmailSolicitado(String email);
}
