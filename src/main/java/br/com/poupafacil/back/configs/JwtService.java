package br.com.poupafacil.back.configs;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.poupafacil.back.entrypoint.pessoa.data.request.LoginRequest;
import br.com.poupafacil.back.gateway.pessoa.PessoaRepository;
import br.com.poupafacil.back.gateway.pessoa.model.PessoaModel;
import br.com.poupafacil.back.usecase.pessoa.data.output.PessoaDataOutput;
import br.com.poupafacil.back.usecase.pessoa.mapper.PessoaUseCaseMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	@Value("${security.jwt.expiracao}")
    private String expiracao;

    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    @Autowired
    private PessoaRepository pessoaRepository;
    
    @Autowired
    private PessoaUseCaseMapper pessoaUseCaseMapper;
    
    public String gerarToken(LoginRequest usuario){
        long expString = Long.valueOf(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);

        return Jwts
                .builder()
                .setSubject(usuario.getEmail())
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
                .compact();
    }
    
    private Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts
                 .parser()
                 .setSigningKey(chaveAssinatura)
                 .parseClaimsJws(token)
                 .getBody();
    }
    
    public boolean tokenValido( String token ){
        try{
            Claims claims = obterClaims(token);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data =
                    dataExpiracao.toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(data);
        }catch (Exception e){
            return false;
        }
    }
    
    public String obterLoginUsuario(String token) throws ExpiredJwtException{
        return (String) obterClaims(token.replace("Bearer ", "")).getSubject();
    }
    
    public PessoaDataOutput obterPessoa(String token) throws ExpiredJwtException{
    	String email = this.obterLoginUsuario(token);
    	PessoaModel pessoa = pessoaRepository.findByEmail(email)
    			.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));
    	
    	
    	return pessoaUseCaseMapper.toPessoaModel(pessoa);
    }
	
}
