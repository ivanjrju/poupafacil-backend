package br.com.poupafacil.back.entrypoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.poupafacil.back.configs.JwtService;
import br.com.poupafacil.back.configs.UsuarioServiceImpl;
import br.com.poupafacil.back.entrypoint.pessoa.data.request.LoginRequest;
import br.com.poupafacil.back.entrypoint.pessoa.data.response.TokenResponse;
import br.com.poupafacil.back.exceptions.SenhaInvalidaException;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class LoginController {
	
	private final UsuarioServiceImpl usuarioService;
    private final JwtService jwtService;
    
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    
    @CrossOrigin
	@PostMapping("/login")
    public ResponseEntity<TokenResponse> autenticar(@RequestBody LoginRequest loginRequest){
        
    	logger.info("email: "+loginRequest.getEmail());
    	logger.info("senha: "+loginRequest.getSenha());
    	
    	try{
            usuarioService.autenticar(loginRequest);
            String token = jwtService.gerarToken(loginRequest);
            return new ResponseEntity<TokenResponse>(TokenResponse.builder().token(token).build(), HttpStatus.OK);
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
