package br.com.poupafacil.back.configs;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.poupafacil.back.exceptions.AuthorizationException;

public class JwtValidarFilter extends BasicAuthenticationFilter {

	public static final String HEADER_ATRIBUTO = "Authorization";
    public static final String ATRIBUTO_PREFIXO = "Bearer ";
	
	public JwtValidarFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String atributo = request.getHeader(HEADER_ATRIBUTO);

        if (atributo == null) {
            chain.doFilter(request, response);
            throw new AuthorizationException("Authorization nao informado.");
        }

        if (!atributo.startsWith(ATRIBUTO_PREFIXO)) {
            chain.doFilter(request, response);
            throw new AuthorizationException("Authorization nao contem o Bearer.");
        }
		
        String token = atributo.replace(ATRIBUTO_PREFIXO, "");
		UsernamePasswordAuthenticationToken authenticationToken = getAuthenticatioToken(token);
		
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		chain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken getAuthenticatioToken(String token) {
		
		String usuario = JWT.require(Algorithm.HMAC512("9e5f90af-5c79-4db1-ba25-03606a332c6d")).build()
				.verify(token)
				.getSubject();
		
		if(usuario == null)
			return null;
		
		return new UsernamePasswordAuthenticationToken(usuario, null, new ArrayList<GrantedAuthority>());
				
	}
}
