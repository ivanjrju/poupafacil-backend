package br.com.poupafacil.back.configs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.poupafacil.back.gateway.pessoa.model.PessoaModel;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DetalheUsuarioData implements UserDetails {

	private static final long serialVersionUID = -2076138752673239018L;

	private Optional<PessoaModel> pessoaModel;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return new ArrayList<GrantedAuthority>();
	}

	@Override
	public String getPassword() {
		
		return pessoaModel.orElse(new PessoaModel()).getSenha();
	}

	@Override
	public String getUsername() {

		return pessoaModel.orElse(new PessoaModel()).getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PessoaModel getPessoaModel() {
		return pessoaModel.get();
	}
}
