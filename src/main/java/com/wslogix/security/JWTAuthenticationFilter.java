package com.wslogix.security;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wslogix.dto.CredenciaisDTO;

/*Filtro que irá interceptar o login e validar as credencias
 *A requisição de validação deve ser do tipo POST com um JSON
 *contendo usuario e senha e a URI deve ser ????/login que é
 *o end point padrão do spring para autenticação
 */

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;    
    private JWTUtil jwtUtil;
    private String empresa;

    public JWTAuthenticationFilter(
    		AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
    	
    	//necessaria para retornar o código 401, caso a autenticação falhe, pois
    	//no Spring 2xx, está retornando 403
    	setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
    	
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
	
    //Esse método tenta fazer a autenticação
	@Override
    public Authentication attemptAuthentication(
    		HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {

		try {
			//pega os dados da requisição (codigo/senha)
			CredenciaisDTO creds = new ObjectMapper()
	                .readValue(req.getInputStream(), CredenciaisDTO.class);
			
			this.empresa = creds.getEmpresa();
			
			//Para autenticar, passa-se o codigo, senha e uma lsta vazia
	        UsernamePasswordAuthenticationToken authToken = 
	        		new UsernamePasswordAuthenticationToken(
	        				creds.getCodigo(), creds.getSenha(), new ArrayList<>());
	        
	        //Chame metodo que realmente faz as verificaçoes. Se for autenticado,
	        //prosegue a requisição e o método abaixo successfulAuthentication é executado. 
	        //Se não, retorna o erro de autenticação
	        
	        Authentication auth = authenticationManager.authenticate(authToken);
	        return auth;
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Caso a autenticação seja feita pelo método anterior, o que deve retornar
	@Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
	
		//pega o codgo do usuário das credenciais
		String username = ((UsuarioSS) auth.getPrincipal()).getUsername();
		//gera o token para o usuario
        String token = jwtUtil.generateToken(username);
        //adiciona o token no cabeçalho da resposta
        //Authorization = nome do cabeçalho
        //Token: um prefixo qualquer que aparecerá no token
        res.addHeader("Authorization", "Token: " + token);
        //disponibiliza o cabeçalho Authorization para que a aplicação possa capiturá-lo
        res.addHeader("access-control-expose-headers", "Authorization");
        
        UsuarioSS uss = (UsuarioSS) auth.getPrincipal();
        String profile = ""+uss.getPerfil();
        
        res.addHeader("Profile", profile);
        //disponibiliza o cabeçalho Profile para que a aplicação possa capiturá-lo
        res.addHeader("access-control-expose-headers", "Profile");

        res.addHeader("Empresa", this.empresa);
        //disponibiliza o cabeçalho Empresa para que a aplicação possa capiturá-la
        res.addHeader("access-control-expose-headers", "Empresa");

	}
	
	//necessária apenas para Spring 2xx, para retornar status correto (401)
	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {
		 
        @Override
        public void onAuthenticationFailure(
        		HttpServletRequest request, HttpServletResponse response, 
        		AuthenticationException exception) throws IOException, ServletException {
            response.setStatus(401);
            response.setContentType("application/json"); 
            response.getWriter().append(json());
        }
        
        private String json() {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", "
                + "\"status\": 401, "
                + "\"error\": \"Não autenticado\", "
                + "\"message\": \"Código ou senha inválidos\", "
                + "\"path\": \"/login\"}";
        }
    }
}
