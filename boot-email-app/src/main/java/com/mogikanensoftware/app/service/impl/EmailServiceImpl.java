package com.mogikanensoftware.app.service.impl;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.mogikanensoftware.app.service.EmailService;
import com.mogikanensoftware.app.service.EmailServiceException;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

	private JavaMailSender mailSender;
	private TemplateEngine templateEngine;
	private MimeMessageHelperCreator mimeMessageHelperCreator;


	@Autowired
	public EmailServiceImpl(@Qualifier("templateEngine")TemplateEngine templateEngine, 
			@Qualifier("mailSender")JavaMailSender mailSender,
			@Qualifier("mimeMessageHelperCreator") MimeMessageHelperCreator mimeMessageHelperCreator){
		this.mailSender = mailSender;
		this.templateEngine = templateEngine;
		this.mimeMessageHelperCreator = mimeMessageHelperCreator;
	}
	
	@Override
	public void sendEmail(final String from, final String to, final String templateName, final String subject, final Map<String, String> params)
			throws EmailServiceException {
		try {
			final Context ctx = new Context();

			params.forEach((k, v) -> ctx.setVariable(k, v));

			final String htmlContent = this.templateEngine.process(templateName, ctx);

			final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
			final MimeMessageHelper message = mimeMessageHelperCreator.create(mimeMessage, "UTF-8");

			message.setFrom(from);
			message.setTo(to);
			message.setSubject(subject);
			message.setText(htmlContent,true);			
			this.mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new EmailServiceException(e.getMessage(), e);
		}

	}
		

}
