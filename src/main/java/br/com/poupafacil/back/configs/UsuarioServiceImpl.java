package br.com.poupafacil.back.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.poupafacil.back.entrypoint.pessoa.data.request.LoginRequest;
import br.com.poupafacil.back.exceptions.SenhaInvalidaException;
import br.com.poupafacil.back.gateway.pessoa.PessoaRepository;
import br.com.poupafacil.back.gateway.pessoa.model.PessoaModel;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

	@Autowired
    private PasswordEncoder encoder;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public UserDetails autenticar(LoginRequest loginRequest){
        UserDetails user = loadUserByUsername(loginRequest.getEmail());
        boolean senhasBatem = encoder.matches(loginRequest.getSenha(), user.getPassword() );

        if(senhasBatem){
            return user;
        }

        throw new SenhaInvalidaException();
    }
	
	@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		PessoaModel usuario = pessoaRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado na base de dados."));

        String[] roles = new String[]{"USER"};

        return User
                .builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }
	
}
