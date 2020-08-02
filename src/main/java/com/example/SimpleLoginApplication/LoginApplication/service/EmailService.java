package com.example.SimpleLoginApplication.LoginApplication.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.example.SimpleLoginApplication.LoginApplication.entity.User;

@Service
public class EmailService {

	@Autowired
	private UserService userService;

	@Autowired
	private JavaMailSender javaMailSender;
	
	public EmailService(UserService userService, JavaMailSender javaMailSender) {
		this.userService = userService;
		this.javaMailSender = javaMailSender;
	}

	public void sendMail(User user) throws Exception
	{
		User userEmailId = userService.fetchByEmailId(user.getEmail());
		// check if user has token
		if(userEmailId!=null) {
		String token = userEmailId.getToken();
		 // send verification mail	 
		 MimeMessage  message = javaMailSender.createMimeMessage();
		 MimeMessageHelper helper = new MimeMessageHelper(message, true);
		 helper.setTo(user.getEmail());
		 helper.setSubject("Email Verification link");
		 helper.setText("http://localhost:8090/activation?token="+token);
		 javaMailSender.send(message);
		}
	}
}
