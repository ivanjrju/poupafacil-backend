package br.com.poupafacil.back.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.AllArgsConstructor;

@EnableWebSecurity
@AllArgsConstructor
public class JwtConfiguracao extends WebSecurityConfigurerAdapter {
	
	private final DetalheUsuarioServiceImpl usuarioService;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		http.csrf().disable()
//		.authorizeHttpRequests()
//		.antMatchers("/login", "/api/pessoas").permitAll()
////		.antMatchers(HttpMethod.POST, "/api/pessoas").permitAll()
//		.anyRequest().authenticated()
//		.and()
//		.addFilter(new JwtAutenticarFilter(authenticationManager()))
//		.addFilter(new JwtValidarFilter(authenticationManager()))
//        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http
        .authorizeRequests().antMatchers("/api/pessoas").permitAll()
        .and()
        .cors()
        .and()
        .authorizeRequests().anyRequest().authenticated();
		
		http
		.formLogin()
		.loginProcessingUrl("/account/login"); // login request uri
		
		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    http.headers().frameOptions().disable();
	    
	    http.addFilterBefore(new JwtAutenticarFilter(authenticationManager()), JwtAutenticarFilter.class);
	    http.addFilterBefore(new JwtValidarFilter(authenticationManager()), JwtValidarFilter.class);
	}
	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//	    web.ignoring().antMatchers("/api/pessoas");
//	    web.
//	}
	
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
