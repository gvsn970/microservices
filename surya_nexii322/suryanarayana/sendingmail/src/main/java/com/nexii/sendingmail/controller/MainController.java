package com.nexii.sendingmail.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexii.sendingmail.service.NotificationService;
import com.nexii.sendingmail.user.User;

@Controller
public class MainController {

	private Logger logger=LoggerFactory.getLogger(MainController.class);
	@Autowired
	private NotificationService notificationService;
	@RequestMapping("/")
	public String indexForm()  {
		return "index";
	}
	@PostMapping("/sendMail")
	public String sendmail(@ModelAttribute User user,HttpServletRequest request) {
		try {
			notificationService.sendNotification(user);
			request.setAttribute("msg", "Massage has been Send Thanku!!..");
			return "index";
		}catch(MailException e) {
			logger.info("semd Mail error:" + e.getMessage());
			
		}
		request.setAttribute("msg", "<span style='color:red'>Massage has been Failed!!..</span>");
		return "index";
	}
}
