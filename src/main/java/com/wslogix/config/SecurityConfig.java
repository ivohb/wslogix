package com.wslogix.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.wslogix.security.JWTAuthenticationFilter;
import com.wslogix.security.JWTAuthorizationFilter;
import com.wslogix.security.JWTUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired //injeta com.wslogix.service.UsuarioDetailsService
	private UserDetailsService userDetailsService; 

	@Autowired //injeta classe que gera o tokem (necessária no registro da classe de autenticação)
	private JWTUtil jwtUtil;

	// Criação dos vetores cujos conteúdos poderão ser acessados livremente.
	
	// métodos GET que poderão ser usados para retornarem
	// informações mesmo antes do login ser realizado.
	private static final String[] PUBLIC_MATCHERS_GET = {
			"/empresa/**",
			"/usuario/popup"
	};

	//métodos POST que precisam ser acessandos antes do login
	//recuperação de senha, por exemplo
	private static final String[] PUBLIC_MATCHERS_POST = {
		"/auth/forgot/**"
	};

	//métodos PATCH que precisam ser acessandos antes do login
	//autorização de empresa, por exemplo
	private static final String[] PUBLIC_MATCHERS_PATCH = {
			"/empresa/**"
	};

	//não basta só criar os vetores aceima. É preciso também dar
	//permissão no método abaixo
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors() //faz com que o Bean do cors seja executado
			.and().csrf().disable(); //desabilita ataque de CSRF, pois essa API não criará seção
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll() //do PUBLIC_MATCHERS_GET
			.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()//e do PUBLIC_MATCHERS_POST
			.antMatchers(HttpMethod.PATCH, PUBLIC_MATCHERS_PATCH).permitAll()//e do PUBLIC_MATCHERS_POST
			.anyRequest().authenticated(); //todo restante deve ser bloqueado

		//não permitirá criação de sessão para o usuário
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		//registro do filtro de autenticação do login 
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));

		//registro do filtro de autorização do login
		http.addFilter(new JWTAuthorizationFilter(
				authenticationManager(), jwtUtil, userDetailsService));

	}
		
	//configuração do cors para permitir acesso a todos os end points por
	//requisições vindas de multiplas fontes. (Aplicações web, APPs, etc)
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(
				Arrays.asList("POST", "GET", "PUT", "DELETE", "PATCH", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
		
	//bean utilizado para encodar a senha do usuário
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//metodo usado para aenformar ao spring security quem é a classe de busca do usuáio
	//pelo código (UsuarioDetailsService) e a forma que a senha é armazenada no banco
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

}
