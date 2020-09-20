package com.nexiilabs.stp.invoice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.time.Month;
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

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.nexiilabs.stp.authentication.FlasMailConfig;
import com.nexiilabs.stp.resource.RequirementsModel;
import com.sun.mail.util.MailSSLSocketFactory;

public class InvoicePDFMail {

	public static boolean sendInvoiceMail(FlasMailConfig flasMailConfig, RequirementsModel requirementsModel,
			InvoiceRequestModel invoiveRequestModel) {
		final String fromuserEmail = flasMailConfig.getFromMailId();
		final String password = flasMailConfig.getFromPassword();
		// logger.info("host::::"+host);
		//System.out.println(flasMailConfig.toString()+"........................flasMailConfig");
		//String monthString = new DateFormatSymbols().getMonths()[invoiveRequestModel.getInvoiceMonth()-1];
		String monthString=Month.of(invoiveRequestModel.getInvoiceMonth()).name();
		System.out.println(monthString+"....monthString");
		Properties props = new Properties();
		MailSSLSocketFactory socketFactory;
		try {
			socketFactory = new MailSSLSocketFactory();
			socketFactory.setTrustAllHosts(true);
			props.put("mail.smtp.ssl.socketFactory", socketFactory);
		} catch (GeneralSecurityException e1) {
			e1.printStackTrace();
		}
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", flasMailConfig.getMailHost());
		props.put("mail.smtp.port", flasMailConfig.getMailPort());
		props.put("mail.smtp.ssl.trust", "*");
		try {

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromuserEmail, password);
				}
			});
			// logger.info("employee.getEmpEmail()::trineesha.mandapati@nexiilabs.com");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromuserEmail, requirementsModel.getPmName()+"-"+monthString+"-"+invoiveRequestModel.getInvoiceYear()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(requirementsModel.getPmEmail()));
			if(!requirementsModel.getPmEmail().equals(requirementsModel.getHmEmail())){
				message.setRecipient(Message.RecipientType.CC, new InternetAddress(requirementsModel.getHmEmail()));
			}
			//Calendar c=Calendar.getInstance();
			//c.get(Calendar.);
			
			message.setSubject("Your Invoice for "+monthString+"-"+invoiveRequestModel.getInvoiceYear());
			String content = " Dear "+requirementsModel.getPmName()+",\n\nYour Invoice For "+monthString+" is attached to this mail.\n \nPlease Find the Attachment.\n\n\n\nRegards,\n"
					+ "Nexii IT Labs Pvt. LTD,\nHyderabad.";
			
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(content);

			// creates multi-part
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// adds attachments

			MimeBodyPart attachPart = new MimeBodyPart();

			try {
				attachPart.attachFile(invoiveRequestModel.getInvoiceFilePath());
			} catch (IOException ex) {
				ex.printStackTrace();
			}

			multipart.addBodyPart(attachPart);

			message.setContent(multipart);
			Transport.send(message);
			System.err.println("sent successfully");
			return true;

		} catch (MessagingException e) {
			e.printStackTrace();

		} catch (RuntimeException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}
	
	public static ResponseEntity<?> downloadPDF(InvoiceRequestModel invoiveRequestModel) {
		try {
			if (invoiveRequestModel.getInvoiceFilePath() != null) {
				File file = new File(invoiveRequestModel.getInvoiceFilePath());
				InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
				if (file.exists()) {
					String mediaType = Files.probeContentType(file.toPath());
					System.err.println("mediaType: " + mediaType);
					System.out.println("in if success download............."+mediaType);
					
					return ResponseEntity.ok()
							.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
							.contentType(MediaType.parseMediaType(mediaType)).contentLength(file.length())
							.body(resource);
				} else {
					System.out.println("in else 1 download.............");
					return new ResponseEntity<String>("File not found to download", HttpStatus.OK);
				}
			} else {
				System.out.println("in else 2 download.............");
				return new ResponseEntity<String>("No files are found  to download", HttpStatus.OK);
			}
			
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("", HttpStatus.ACCEPTED);
	}

}




