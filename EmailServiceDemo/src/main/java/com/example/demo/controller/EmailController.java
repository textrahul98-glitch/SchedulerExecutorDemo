package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.EmailJob;
import com.example.demo.model.EmailRequest;
import com.example.demo.service.EmailQueueService;

@RestController
@RequestMapping("/email")
public class EmailController {

	@Autowired
	private EmailQueueService emailQueueService;

	@PostMapping("/queue")
	public String queueEmails(@RequestBody EmailRequest request) {

		if (request.getRecipients() == null || request.getRecipients().isEmpty() || request.getBody() == null
				|| request.getSubject() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subject, body is required");
		}

	List<EmailJob> jobs=	request.getRecipients()
		.stream().
		filter(r -> r != null && !r.isBlank())
				.map(r -> new EmailJob(r.trim(), request.getSubject(), request.getBody()))
				.collect(Collectors.toList());

	jobs.forEach(emailQueueService::addEmail);
	
	return jobs.size()+"  Email send Successfully";
	
	}

}
