package com.mogikanensoftware.app.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import com.mogikanensoftware.app.service.EmailService;
import com.mogikanensoftware.app.service.EmailServiceException;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class MailSenderControllerTest {

	private MailSenderController controller;

	@Before
	public void setUp() throws Exception {

		EmailService emailService = Mockito.mock(EmailService.class);

		controller = new MailSenderController(emailService);
	}

	@After
	public void tearDown() throws Exception {
		controller = null;
	}

	@Test
	@Parameters({ 
        "John, Doe", 
        "John, ",
        ", Doe",
        ", "})
	public void testSendHelloEmail(String firstName, String lastName) throws EmailServiceException {
		String result = controller.sendHelloEmail(firstName, lastName);
		Assert.assertNotNull(result);
		Assert.assertTrue(result.equals(HttpStatus.ACCEPTED.toString()));
	}

}
