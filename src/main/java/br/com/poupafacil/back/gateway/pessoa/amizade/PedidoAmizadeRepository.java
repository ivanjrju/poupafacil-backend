package br.com.poupafacil.back.gateway.pessoa.amizade;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.poupafacil.back.gateway.pessoa.amizade.model.PedidoAmizadeModel;

public interface PedidoAmizadeRepository extends JpaRepository<PedidoAmizadeModel, Long>{

	List<PedidoAmizadeModel> findAllByEmailSolicitado(String email);
	PedidoAmizadeModel findAllByEmailSolicitadoAndEmailSolicitante(String email, String solicitante);
}
