package br.com.poupafacil.back.configs;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.poupafacil.back.gateway.pessoa.PessoaRepository;
import br.com.poupafacil.back.gateway.pessoa.model.PessoaModel;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DetalheUsuarioServiceImpl implements UserDetailsService {

	private PessoaRepository pessoaRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<PessoaModel> pessoaModel = pessoaRepository.findByEmail(username);
		
		return new DetalheUsuarioData(pessoaModel);
	}
}
