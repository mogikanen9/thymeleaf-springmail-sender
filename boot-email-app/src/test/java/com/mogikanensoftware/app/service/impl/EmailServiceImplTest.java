package com.mogikanensoftware.app.service.impl;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

import com.mogikanensoftware.app.service.EmailService;
import com.mogikanensoftware.app.service.EmailServiceException;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceImplTest {

	private EmailService emailService;

	@Mock
	TemplateEngine templateEngine;

	@Mock
	JavaMailSender mailSender;

	@Before
	public void setUp() throws Exception {
		emailService = new EmailServiceImpl(templateEngine, mailSender);
	}

	@After
	public void tearDown() throws Exception {
		emailService = null;
	}

	@Test
	public void testSendEmail() throws EmailServiceException {
		Map<String,String> params = new HashMap<>();
		params.put("firstName", "Jina");
		params.put("lastName", "Doe");
		//emailService.sendEmail("abc@wer.com", "qwe@q.com", "welcomeTemplate", "hi", params);
	}

}
