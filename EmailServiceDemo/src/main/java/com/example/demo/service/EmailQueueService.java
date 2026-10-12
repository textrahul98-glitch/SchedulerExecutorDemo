package com.example.demo.service;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Service;

import com.example.demo.model.EmailJob;


@Service
public class EmailQueueService {
	
	private final ConcurrentLinkedQueue<EmailJob>  queue= new ConcurrentLinkedQueue<>();
	
	public void addEmail(EmailJob job) {
		queue.offer(job);
	}
	
	public EmailJob getNextEmail() {
		return queue.poll();
	}

	public int getQueueSize() {
		
		return queue.size();
		
	}
	
}
