package br.com.poupafacil.back.configs.interceptors;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.poupafacil.back.gateway.pessoa.PessoaRepository;
import br.com.poupafacil.back.gateway.pessoa.model.PessoaModel;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {

	private PessoaRepository pessoaRepository;
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
		String token = request.getHeader("Authorization");
		if(Objects.nonNull(token)) {
        	
        	String idPessoa = JWT.require(Algorithm.HMAC512("9e5f90af-5c79-4db1-ba25-03606a332c6d")).build()
    				.verify(token.replace("Bearer ", ""))
    				.getSubject();
        	
        	PessoaModel pessoa = pessoaRepository.getById(Long.parseLong(idPessoa));
        	
        	request.setAttribute("pessoa", pessoa);
        	
        	return true;        	
        }
        return false;
    }
}
