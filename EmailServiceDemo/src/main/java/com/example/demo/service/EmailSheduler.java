package com.example.demo.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.model.EmailJob;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmailSheduler {
	
  private final EmailQueueService emailQueueService;
  private final EmailSenderService emailSenderService;
  
  @Scheduled(fixedRate = 30000)
  public void processPendingEmails() {

      log.info("Scheduler started. Queue size: {}",
              emailQueueService.getQueueSize());

      int submitted = 0;

      while (submitted < 20) {
          EmailJob job = emailQueueService.getNextEmail();

          if (job == null) {
              break;
          }

          log.info("Submitting email to {}", job.getTo());

          emailSenderService.sendEmail(job);
          submitted++;
      }

      log.info("Submitted {} email jobs", submitted);
  }
}
