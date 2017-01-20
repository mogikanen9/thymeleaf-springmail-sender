package com.mogikanensoftware.app.service;

import java.util.Map;

public interface EmailService {

	 void sendEmail(String from, String to, String templateName, String subject, Map<String,String> params) throws EmailServiceException;
}
