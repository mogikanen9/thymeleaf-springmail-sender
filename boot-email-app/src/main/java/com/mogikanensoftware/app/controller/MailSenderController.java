package com.mogikanensoftware.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mogikanensoftware.app.service.EmailService;
import com.mogikanensoftware.app.service.EmailServiceException;

@RestController
@RequestMapping("emails")
public class MailSenderController {

	private EmailService emailService;
	
	public MailSenderController(@Autowired EmailService emailService){
		this.emailService = emailService;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/send")
	public String sendHelloEmail(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName)
			throws EmailServiceException {
		Map<String,String> params = new HashMap<>();
		params.put("firstName", firstName);
		params.put("lastName", lastName);
		emailService.sendEmail("me@wer.com", "joe@qwe.com", "welcomeTemplate", "Hello from me", params);
		return HttpStatus.ACCEPTED.toString();
	}

}
