package com.mogikanensoftware.app.service.impl;

import java.util.HashMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.mogikanensoftware.app.service.EmailService;
import com.mogikanensoftware.app.service.EmailServiceException;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceWithParamCheckImplTest {

	private EmailServiceWithParamCheckImpl emailService;

	@Mock
	private EmailService targetService;

	@Before
	public void setUp() throws Exception {
		emailService = new EmailServiceWithParamCheckImpl(targetService);
	}

	@After
	public void tearDown() throws Exception {
		emailService = null;
	}

	@Test
	public void testSendEmail() throws EmailServiceException {
		emailService.sendEmail("from", "to", "welcomeTemplate", "you didjey", new HashMap<>());
		
		try {
			emailService.sendEmail(null, "to", "welcomeTemplate", "you didjey", new HashMap<>());
			Assert.fail("Unreacheable step");
		} catch (EmailServiceException e) {
			Assert.assertTrue(e.getMessage().contains("Parameter 'from' cannot be empty"));
		}
		
		try {
			emailService.sendEmail("from", "", "welcomeTemplate", "you didjey", new HashMap<>());
			Assert.fail("Unreacheable step");
		} catch (EmailServiceException e) {
			Assert.assertTrue(e.getMessage().contains("Parameter 'to' cannot be empty"));
		}
		
		try {
			emailService.sendEmail("from", "to", "", "you didjey", new HashMap<>());
			Assert.fail("Unreacheable step");
		} catch (EmailServiceException e) {
			Assert.assertTrue(e.getMessage().contains("Parameter 'templateName' cannot be empty"));
		}
		
		try {
			emailService.sendEmail("from", "to", "s", null, new HashMap<>());
			Assert.fail("Unreacheable step");
		} catch (EmailServiceException e) {
			Assert.assertTrue(e.getMessage().contains("Parameter 'subject' cannot be empty"));
		}
		
		try {
			emailService.sendEmail("from", "to", "s", "lenat", null);
			Assert.fail("Unreacheable step");
		} catch (EmailServiceException e) {
			Assert.assertTrue(e.getMessage().contains("Map with email parameters cannot be null"));
		}
	}

	@Test
	public void testValidateEmptyString() {
		try {
			emailService.validateEmptyString("moe", "12345");
		} catch (EmailServiceException e) {
			Assert.fail("EmailServiceException should not be thrown!");
		}

		try {
			emailService.validateEmptyString("moe", null);
			Assert.fail("Unreacheable step");
		} catch (EmailServiceException e) {
			Assert.assertTrue(e.getMessage().contains("cannot be empty"));
		}

		try {
			emailService.validateEmptyString("moe", "");
			Assert.fail("Unreacheable step");
		} catch (EmailServiceException e) {
			Assert.assertTrue(e.getMessage().contains("cannot be empty"));
		}
	}

}
