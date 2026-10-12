package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.model.EmailJob;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailSenderService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String fromEmail;
	
	@Async("emailExecutor")
	public void sendEmail(EmailJob job) {
		
		try{
			SimpleMailMessage message= new SimpleMailMessage();
		
		
		message.setFrom(fromEmail);
		message.setTo(job.getTo());
		message.setSubject(job.getSubject());
		message.setText(job.getBody());
		mailSender.send(message);
		log.info("Email send successfully to {}", job.getTo());
		}catch (Exception e) {
			log.error("mail sending fails");
		}
		
	}

}
