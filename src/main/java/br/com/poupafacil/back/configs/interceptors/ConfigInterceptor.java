package br.com.poupafacil.back.configs.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ConfigInterceptor extends WebMvcConfigurerAdapter  {

	private final AuthorizationInterceptor inperceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(inperceptor);
    }
}
