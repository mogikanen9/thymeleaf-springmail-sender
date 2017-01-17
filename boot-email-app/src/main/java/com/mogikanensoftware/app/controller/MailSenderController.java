package com.mogikanensoftware.app.controller;

import java.util.Arrays;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RestController
@RequestMapping("emails")
public class MailSenderController {

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

	@RequestMapping(method = RequestMethod.GET, path = "/send")
	public String sendHelloEmail(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName)
			throws MessagingException {

		final Context ctx = new Context();
		ctx.setVariable("firstName", firstName);
		ctx.setVariable("lastName", firstName);
		ctx.setVariable("subscriptionDate", new Date());
		ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));

		final String htmlContent = this.templateEngine.process("welcome.html", ctx);

		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
		message.setFrom("sender@example.com");
		message.setTo("recipient@example.com");
		message.setSubject("This is the message subject");
		message.setText(htmlContent);
		this.mailSender.send(mimeMessage);

		return HttpStatus.ACCEPTED.toString();
	}

}
