package br.com.poupafacil.back.gateway.grupo;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.poupafacil.back.gateway.grupo.model.GrupoModel;

public interface GrupoRepository extends JpaRepository<GrupoModel, Long>{

}
