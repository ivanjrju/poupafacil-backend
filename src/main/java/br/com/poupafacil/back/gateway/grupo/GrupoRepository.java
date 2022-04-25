package br.com.poupafacil.back.gateway.grupo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.poupafacil.back.gateway.grupo.model.GrupoModel;

public interface GrupoRepository extends JpaRepository<GrupoModel, Long>{
	
	public List<GrupoModel> findByPessoasIdPessoa(Long idPessoa);
	
}
