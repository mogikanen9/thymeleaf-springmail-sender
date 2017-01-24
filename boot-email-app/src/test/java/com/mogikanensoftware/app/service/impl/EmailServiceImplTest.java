package com.mogikanensoftware.app.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.mogikanensoftware.app.service.EmailServiceException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TemplateEngine.class)
public class EmailServiceImplTest {

	private EmailServiceImpl emailService;

	@Before
	public void setUp() throws Exception {
		JavaMailSender mailSender = Mockito.mock(JavaMailSender.class);
		TemplateEngine templateEngine = PowerMockito.mock(TemplateEngine.class);
		ITemplateResolver templateResolver = PowerMockito.mock(ITemplateResolver.class);
		MimeMessageHelperCreator mimeMessageHelperCreator = PowerMockito.mock(MimeMessageHelperCreator.class);

		MimeMessageHelper mimeMessageHelper = PowerMockito.mock(MimeMessageHelper.class);
		Mockito.doThrow(new MessagingException("Invalid from")).when(mimeMessageHelper).setFrom("gavno@qwe.com");
		PowerMockito.when(mimeMessageHelperCreator.create(Mockito.any(), Mockito.any())).thenReturn(mimeMessageHelper);

		templateEngine.setTemplateResolver(templateResolver);
		emailService = new EmailServiceImpl(templateEngine, mailSender, mimeMessageHelperCreator);
		PowerMockito.when(templateEngine.process(Mockito.anyString(), Mockito.any())).thenReturn("email html content");
	}

	@After
	public void tearDown() throws Exception {
		emailService = null;
	}

	@Test
	public void testSendEmail() throws EmailServiceException {
		Map<String, String> params = new HashMap<>();
		params.put("firstName", "Jina");
		params.put("lastName", "Doe");
		emailService.sendEmail("abc@wer.com", "qwe@q.com", "welcomeTemplate", "hi", params);
	}

	@Test(expected = EmailServiceException.class)
	public void testSendEmailWithMimeException() throws MessagingException, EmailServiceException {
		Map<String, String> params = new HashMap<>();
		params.put("firstName", "Jina");
		params.put("lastName", "Doe");
		emailService.sendEmail("gavno@qwe.com", "qwe@q.com", "welcomeTemplate", "hi", params);
		Assert.fail();

	}

}
