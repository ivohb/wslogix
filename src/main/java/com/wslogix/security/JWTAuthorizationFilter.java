package com.wslogix.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private JWTUtil jwtUtil;
	private UserDetailsService userDetailsService;
	
	public JWTAuthorizationFilter(
			AuthenticationManager authenticationManager, 
			JWTUtil jwtUtil, UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;		
	}

	//Intercepta a requisição e vê se o usuário está autorizado
	@Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

		//pega o conteúdo do cabeçalho da requisição(token)		
		String header = request.getHeader("Authorization");
		//Se o cabeçalho começar por "Token: " 
		if (header != null && header.startsWith("Token: ")) {
			//chama rotina de validação do token passando o token (token começa
			// a partir da posção 7 do cabeçalho) - retorna null se token inválido
			UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7));
			
			if (auth != null) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		//após os testes de validade, libera o prosseguimento da requisição
		chain.doFilter(request, response);

	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		//chama rotina de validação do token
		if (jwtUtil.tokenValido(token)) {
			//chama rotina que pega o codigo do usuário a partir do token
			String username = jwtUtil.getUsername(token);
			//busca usuário no banco de dados
			UserDetails user = userDetailsService.loadUserByUsername(username);
			//de posse do usuário, gera o objeto UsernamePasswordAuthenticationToken,
			//passando como argumento o usuário, credencias (null) e os perfis
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		}
		return null;
	}
}
