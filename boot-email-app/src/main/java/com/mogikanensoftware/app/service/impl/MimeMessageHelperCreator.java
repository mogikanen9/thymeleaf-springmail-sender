package com.mogikanensoftware.app.service.impl;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component("mimeMessageHelperCreator")
public class MimeMessageHelperCreator {

	public MimeMessageHelper create(MimeMessage mimeMessage, String encoding) {
		return new MimeMessageHelper(mimeMessage, encoding);
	}

}
