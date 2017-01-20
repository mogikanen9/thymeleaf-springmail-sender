package com.mogikanensoftware.app.service.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.mogikanensoftware.app.service.EmailService;
import com.mogikanensoftware.app.service.EmailServiceException;

@Service("emailServiceWithParamCheck")
@Primary
public class EmailServiceWithParamCheckImpl implements EmailService {

	private static final String PARAMETER_S_CANNOT_BE_EMPTY = "Parameter '%s' cannot be empty";

	private EmailService target;

	@Autowired
	public EmailServiceWithParamCheckImpl(@Qualifier("emailService") EmailService target) {
		this.target = target;
	}

	@Override
	public void sendEmail(String from, String to, String templateName, String subject, Map<String, String> params)
			throws EmailServiceException {

		validateEmptyString("from", from);
		validateEmptyString("to", to);
		validateEmptyString("templateName", templateName);
		validateEmptyString("subject", subject);

		if (params == null) {
			throw new EmailServiceException("Map with email parameters cannot be null");
		}

		target.sendEmail(from, to, templateName, subject, params);

	}

	protected void validateEmptyString(String paramName, String paramValue) throws EmailServiceException {
		if (StringUtils.isEmpty(paramValue))
			throw new EmailServiceException(String.format(PARAMETER_S_CANNOT_BE_EMPTY, paramName));
	}
}
