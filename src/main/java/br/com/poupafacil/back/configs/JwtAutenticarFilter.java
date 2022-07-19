package br.com.poupafacil.back.configs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.poupafacil.back.gateway.pessoa.model.PessoaModel;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtAutenticarFilter extends UsernamePasswordAuthenticationFilter{

	private final AuthenticationManager authenticationManager;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			
			PessoaModel pessoaModel = new ObjectMapper().readValue(request.getInputStream(), PessoaModel.class);
			
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					pessoaModel.getEmail(),
					pessoaModel.getSenha(),
					new ArrayList<GrantedAuthority>()));
		} catch (Exception e) {
			throw new RuntimeException("Falha ao autenticar usuario");
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		DetalheUsuarioData usuarioData = (DetalheUsuarioData) authResult.getPrincipal();
		
		String token = JWT.create()
				.withSubject(usuarioData.getPessoaModel().getIdPessoa().toString())
				.withExpiresAt(new Date(System.currentTimeMillis() + 600_000))
				.sign(Algorithm.HMAC512("9e5f90af-5c79-4db1-ba25-03606a332c6d"));
		
		response.getWriter().write(token);
		response.getWriter().flush();
	}
}
