package br.com.poupafacil.back.configs.interceptors;

import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.poupafacil.back.gateway.pessoa.PessoaRepository;
import br.com.poupafacil.back.gateway.pessoa.model.PessoaModel;
import br.com.poupafacil.back.usecase.pessoa.mapper.PessoaUseCaseMapper;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {

	private PessoaRepository pessoaRepository;
	private PessoaUseCaseMapper pessoaUseCaseMapper;
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		System.out.println(request.getRequestURI());
		
		String token = request.getHeader("Authorization");
		
		if(Objects.nonNull(token)) {
        	
			String idPessoa = JWT.require(Algorithm.HMAC512("9e5f90af-5c79-4db1-ba25-03606a332c6d")).build()
    				.verify(token.replace("Bearer ", ""))
    				.getSubject();
        	
        	Optional<PessoaModel> pessoa = pessoaRepository.findById(Long.parseLong(idPessoa));
        	
        	ObjectMapper mapper = new ObjectMapper();
        	if(pessoa.isPresent()) {
        		String pessoaJson = mapper.writeValueAsString(pessoaUseCaseMapper.toPessoaModel(pessoa.get()));
            	request.setAttribute("pessoa", pessoaJson);
            	
            	System.out.println("Interceptor OK");
            	return true;
        	}
        	
        }
		System.out.println("Interceptor NOK");
        return true;
    }
}
