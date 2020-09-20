package com.nexiilabs.stp.util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.nexiilabs.stp.authentication.FlasMailConfig;
import com.nexiilabs.stp.user.CreateUserModel;
import com.sun.mail.util.MailSSLSocketFactory;

public class MailUtils {

	public static void sendUserCredentialsMail(CreateUserModel userModel, FlasMailConfig flasMailConfig){
		final String fromuserEmail = flasMailConfig.getFromMailId();
		final String password = flasMailConfig.getFromPassword();
		Properties props = new Properties();
		MailSSLSocketFactory socketFactory = null;
		try {
			socketFactory = new MailSSLSocketFactory();
		} catch (GeneralSecurityException e1) {
			e1.printStackTrace();
			//throw new MessageSendingFailedException();
		}
		socketFactory.setTrustAllHosts(true);
		props.put("mail.smtp.ssl.socketFactory", socketFactory);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", flasMailConfig.getMailHost());
		props.put("mail.smtp.port", Integer.parseInt(flasMailConfig.getMailPort()));
		props.put("mail.smtp.ssl.trust", "*");
		try {
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {

					return new PasswordAuthentication(fromuserEmail, password);
				}
			});
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromuserEmail, flasMailConfig.getFromUserName()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userModel.getEmail()));

			message.setSubject("STP user credentials");
			String content = "Hi " + userModel.getFirstName()+" "+userModel.getLastName()
					+ ",\n Welcome to STP,please find below login credentials to access the tool\n"
					+ "Email : " + userModel.getEmail() 
					+ "\n Password : " + userModel.getUserPassword() + "" + "\n"
					+"\n If logging in from Nexii network (Hyderabad), use http://192.168.50.55 to login.\n" + 
					"If logging in from outside Nexii network please use http://stp.nexiilabs.com\n" + 
					"For security reasons we suggest you change your password after login.\n\n"
					+ "Thanks,\nSTP Team.";

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(content);
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
			//throw new MessageSendingFailedException();

		} catch (RuntimeException e) {
			e.printStackTrace();
			//throw new MessageSendingFailedException();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//throw new MessageSendingFailedException();
		}
	}
	
	public static void sendForgotPasswordMail(CreateUserModel userModel, FlasMailConfig flasMailConfig){
		final String fromuserEmail = flasMailConfig.getFromMailId();
		final String password = flasMailConfig.getFromPassword();
		Properties props = new Properties();
		MailSSLSocketFactory socketFactory = null;
		try {
			socketFactory = new MailSSLSocketFactory();
		} catch (GeneralSecurityException e1) {
			e1.printStackTrace();
			//throw new MessageSendingFailedException();
		}
		socketFactory.setTrustAllHosts(true);
		props.put("mail.smtp.ssl.socketFactory", socketFactory);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", flasMailConfig.getMailHost());
		props.put("mail.smtp.port", Integer.parseInt(flasMailConfig.getMailPort()));
		props.put("mail.smtp.ssl.trust", "*");
		try {
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {

					return new PasswordAuthentication(fromuserEmail, password);
				}
			});
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromuserEmail, flasMailConfig.getFromUserName()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userModel.getEmail()));

			message.setSubject("STP forgot user credentials");
			String content = "Hi " + userModel.getFirstName()+userModel.getLastName()
					+ ",\n Please find below login credentials to access the tool\n\n"
					+ "Email : " + userModel.getEmail()
					+ "\n Password : " + userModel.getUserPassword() + "" + "\n\n"
					+ "For security reasons we suggest you change your password after login.\n\n"
					+ "Thanks,\nSTP Team.";

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(content);
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
			//throw new MessageSendingFailedException();

		} catch (RuntimeException e) {
			e.printStackTrace();
			//throw new MessageSendingFailedException();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//throw new MessageSendingFailedException();
		}
	}

}
