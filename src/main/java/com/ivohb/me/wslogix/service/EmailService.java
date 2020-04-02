package com.ivohb.me.wslogix.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.ivohb.me.wslogix.model.Documento;
import com.ivohb.me.wslogix.model.Usuario;

public interface EmailService {

	//corpo com texto plano
	void ConfirmaEmail(Usuario obj);	
	void enviaEmail(SimpleMailMessage msg);

	//corpo com texto no formato HTML
	void ConfirmaHtmlEmail(Documento obj);
	void enviaHtmlEmail(MimeMessage msg);
	void sendNewPasswordEmail(Usuario usuario, String newPass);
}
