package com.nexii.sendmailinspringboot;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
	
	@Autowired
	private SmtpMailSender smtpMailSender;

	@RequestMapping("/send-mail")
	public void sendMail() throws MessagingException {
		
		smtpMailSender.send("padmavathi.rayani@nexiilabs.com", "Test mail from SpringBoot", "hi..padmavati It is comeing from Mail Api");
		
	}
	

}