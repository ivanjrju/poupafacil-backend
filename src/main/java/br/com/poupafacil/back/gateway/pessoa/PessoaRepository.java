package br.com.poupafacil.back.gateway.pessoa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.poupafacil.back.gateway.pessoa.model.PessoaModel;

public interface PessoaRepository extends JpaRepository<PessoaModel, Long>{
	
	Optional<PessoaModel> findByEmail(String email);
}
