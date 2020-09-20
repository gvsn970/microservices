package com.nexii.sendingmail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.nexii.sendingmail.user.User;

@Service
public class NotificationService {

	private JavaMailSender javaMailSender;

	@Autowired
	public NotificationService(JavaMailSender javaMailSender) {
		super();
		this.javaMailSender = javaMailSender;
	}

	public void sendNotification(User user) throws MailException{
		
		SimpleMailMessage mail=new SimpleMailMessage();
			mail.setTo(user.getTo());
			mail.setFrom("gvsn970@gmail.com");
			mail.setSubject(user.getSubject());
			mail.setText(user.getMessage());
			javaMailSender.send(mail);
	}
}
