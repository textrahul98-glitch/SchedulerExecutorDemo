package com.example.demo.model;

import java.util.List;

import lombok.Data;

@Data
public class EmailRequest {
	
	private List<String> recipients;
	private String subject;
	private String body;

}
