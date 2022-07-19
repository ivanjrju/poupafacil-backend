package br.com.poupafacil.back.usecase.permissao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.poupafacil.back.exceptions.PermissaoException;
import br.com.poupafacil.back.gateway.grupo.GrupoRepository;
import br.com.poupafacil.back.gateway.grupo.model.GrupoModel;
import br.com.poupafacil.back.usecase.permissao.PermissaoUseCase;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PermissaoUseCaseImpl implements PermissaoUseCase {

//	private GrupoRepository grupoRepository;
//	
//	private Long validarPessoa(String authorization) {
//		
//		String idPessoa = JWT.require(Algorithm.HMAC512("9e5f90af-5c79-4db1-ba25-03606a332c6d")).build()
//				.verify(authorization.replace("Bearer ", ""))
//				.getSubject();
//		
//		return Long.parseLong(idPessoa);
//	}
//	
//	@Override
//	public Long permitirAcessoPessoa(Long idPessoa, String authorization) throws Exception {
//		
//		Long idPessoaConvertido = validarPessoa(authorization);
//		
//		if(idPessoa != idPessoaConvertido)
//			throw new PermissaoException("Token diferente do usuario solicitado");
//		
//		return idPessoaConvertido;
//	}
//	
//	@Override
//	public void permitirAcessoGrupo(Long idPessoa, Long idGrupo, String authorization) throws Exception {
//		
//		permitirAcessoPessoa(idPessoa, authorization);
//		
//		List<GrupoModel> gruposModel = grupoRepository.findByPessoasIdPessoa(idPessoa);
//		
//		Optional<GrupoModel> grupo = gruposModel.stream().filter(grupoModel -> grupoModel.getIdGrupo() == idGrupo).findFirst();
//		
//		if(grupo.isEmpty())
//			throw new PermissaoException("Usuario nao faz parte do grupo selecionado.");
//	}
//
//	@Override
//	public Long permitirAcessoParticipantesGrupo(List<Long> participantes, String authorization) throws Exception {
//		
//		Long idPessoaConvertido = validarPessoa(authorization);
//		
//		if(!participantes.contains(idPessoaConvertido))
//			throw new PermissaoException("Usuario deve conter na lista dos participantes");
//			
//		return idPessoaConvertido;
//	}	
}
