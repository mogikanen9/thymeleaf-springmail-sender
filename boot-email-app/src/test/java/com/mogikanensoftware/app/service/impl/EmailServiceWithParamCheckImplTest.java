package com.mogikanensoftware.app.service.impl;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
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
	}

	@Test
	public void testValidateEmptyString() {
		//fail("Not yet implemented");
	}

}
