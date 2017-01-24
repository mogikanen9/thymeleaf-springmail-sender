package com.mogikanensoftware.app.service.impl;

import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.Assert;

public class MimeMessageHelperCreatorTest {

	@Test
	public void testCreate() {

		MimeMessage mimeMessage =  Mockito.mock(MimeMessage.class);
		MimeMessageHelperCreator creator = new MimeMessageHelperCreator();
		MimeMessageHelper helper =  creator.create(mimeMessage, "UTF-8");
		Assert.notNull(helper);
		Assert.isTrue(helper.getEncoding().equalsIgnoreCase("UTF-8"));
	}

}
