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

@Service
public class EmailServiceImpl implements EmailService {

	private JavaMailSender mailSender;
	private TemplateEngine templateEngine;

	@Autowired
	@Qualifier("templateEngine")
	public void setTemplateEngine(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	@Autowired
	@Qualifier("mailSender")
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void sendEmail(String from, String to, String templateName, String subject, Map<String, String> params)
			throws EmailServiceException {
		try {
			final Context ctx = new Context();

			params.forEach((k, v) -> ctx.setVariable(k, v));

			final String htmlContent = this.templateEngine.process(templateName, ctx);

			final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
			final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");

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
