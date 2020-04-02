package com.ivohb.me.wslogix.service;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ivohb.me.wslogix.model.Documento;
import com.ivohb.me.wslogix.model.Usuario;

public abstract class AbstractEmailService implements EmailService {
		
	//busca o email da propriadade default.sender
	
	@Value("${default.sender}")
	private String sender;

	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired 
	private JavaMailSender javaMailSender;

	@Override
	public void ConfirmaEmail(Usuario obj) {
		SimpleMailMessage sm = preparaMensagemUsuario(obj);
		enviaEmail(sm);
	}

	protected SimpleMailMessage preparaMensagemUsuario(Usuario obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		//que vai receber o email
		sm.setTo(obj.getEmail());
		//quem vai enviar o email
		sm.setFrom(sender);
		//assunt do email 
		sm.setSubject("Alterar sua senha: ");
		//data do envio do email
		sm.setSentDate(new Date(System.currentTimeMillis()));
		//corpo do email
		sm.setText(obj.toString());
		return sm;
	}
	
	@Override
	public void ConfirmaHtmlEmail(Documento obj) {
		
		try {
			MimeMessage mm = preparaMensagemHtml(obj);
			enviaHtmlEmail(mm);	
		} catch (MessagingException e) {
			//se ão for possivel enviar email html,
			//enviar com texto plano
			//ConfirmaEmail(obj);
		}			
	}
	
	protected MimeMessage preparaMensagemHtml(Documento obj) throws MessagingException  {
		MimeMessage mm = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mm, true);
		mmh.setTo("emial do destinatario ");
		mmh.setFrom(sender);
		mmh.setSubject("assunto do email");
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateDocumento(obj), true);		
		return mm;
	}

	//responsável por preencher o template Html com os dados do documento
	
	protected String htmlFromTemplateDocumento(Documento obj) {
		Context context = new Context();
		//envia o documento para o template. Quanado for criado o
		//template de documento, mude a linha abaixo para documento
		context.setVariable("pedido", obj);
		//processa o preenchimento dos dados no template e retorna uma string
		return templateEngine.process("email/confirmacaoPedido", context);		
	}
	
	@Override
	public void sendNewPasswordEmail(Usuario usuario, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(usuario, newPass);
		enviaEmail(sm);
	}
	
	protected SimpleMailMessage prepareNewPasswordEmail(Usuario usuario, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(usuario.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + newPass);
		return sm; 
	}
}
