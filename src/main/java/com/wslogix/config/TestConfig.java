package com.wslogix.config;

/*
 * Essa classe contem as configurações do ambiente de teste
 * Todo método anotado com @Bean se transforma num
 * componente e o mesmo fica disponível dentro de
 * todo o sistema
 */
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.wslogix.service.DBService;
import com.wslogix.service.EmailService;
import com.wslogix.service.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;
	
	/*Ao startar a aplicação, dispara o método abaixo para gravação de tabelas */
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.insereDados();
		return true;
	}
		
	@Bean 
	public EmailService emailService() {
		return new MockEmailService();
	}

}
