package br.com.poupafacil.back.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private UsuarioServiceImpl usuarioService;
	
	@Autowired
    private JwtService jwtService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	@Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, usuarioService);
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioService)
		.passwordEncoder(passwordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) {
		web
			.ignoring().antMatchers("/swagger-ui/**", "/api-docs/**")
		.and()
			.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors()
		.and()
		.csrf().disable()
        .authorizeRequests()
            .antMatchers("/api/pessoas/**")
                .hasAnyRole("USER")
            .antMatchers("/api/grupos/**")
                .hasAnyRole("USER")
            .antMatchers("/api/despesas/**")
                .hasRole("USER")
            .antMatchers(HttpMethod.DELETE,"/api/despesas/**")
            	.hasRole("USER")
            .antMatchers("/login","/api/pessoas/**")
                .permitAll()
        	.antMatchers(HttpMethod.OPTIONS, "/**")
        		.permitAll()
            .anyRequest().authenticated()
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    
	}
}
