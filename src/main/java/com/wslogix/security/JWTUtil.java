package com.wslogix.security;


import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {


	//palavra secreta do application.properties
	@Value("${jwt.secret}")
	private String secret;

	//tempo de expiração do token 
	@Value("${jwt.expiration}")
	private Long expiration;
	
	/*geração do token para o usuario, a partir do codigo, da palavra secreta
	 * e do tempo de empiração do arquivo application.properties
	 */
	public String generateToken(String codigo) {
		return Jwts.builder()
				.setSubject(codigo)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}
	
	//Se token contém usuario e tempo de expiração(ms) maior 
	//que tempo atual(ms), valida o token
	
	public boolean tokenValido(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if (username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}

	//retorna código do usuário
	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}

	//recupera usuário de tempo de expiração do token
	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		}
		catch (Exception e) {
			return null;
		}
	}

}
