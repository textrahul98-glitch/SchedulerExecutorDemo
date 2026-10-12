package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.model.EmailJob;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RecurringEmailScheduler {

	@Autowired
	private EmailSenderService emailSenderService;

	//@Scheduled(fixedRate = 60000)// this is for 1 min
	@Scheduled(cron = "0 */2 * * * *")
	public void sendCongratulationsEmail() {

		log.info("Triggering motivational study email");

		String[] recipients = { "sushanttambe04@gmail.com",
				"shashisingh1616@gmail.com",
				"naikomkar741@gmail.com","shankarnarwade209@gmail.com","chinmayrokade9@gmail.com",
				"dhaneshsawane19@gmail.com","ohol.harshada15@gmail.com","vishakhajagtap2001@gmail.com",
				"dushantshende@gmail.com",
				"onlinemaheshdiwan@gmail.com", 
				"rajeshsmahor@gmail.com",
				"pravin44618@gmail.com",
				"indlavikas57@gmail.com",
				"rudra25parmeshwar@gmail.com",
				"chakotkarumesh@gmail.com",
				"aniketisane07@gmail.com", 
				"nishigandhashete8396@gmail.com"

				
};

		for (String recipient : recipients) {

			EmailJob job = new EmailJob(recipient, "Java Full Stack Batch – Your Success Starts with Daily Practice!",
				"""
				Dear Students,
				
				I hope you are all doing well!
				
				Learning Java Full Stack Development is a great step toward building a successful career in the IT industry. Remember, becoming a good developer requires consistency, practice, patience, and a willingness to learn every day.
				
				Do not think that learning Java, Spring Boot, Angular, SQL, and other technologies is difficult. Break your learning into small steps and practise regularly.
				
				Remember these important points:
				
				Practise Java programming every day.
				
				Understand concepts instead of simply memorising code.
				
				Build projects using Spring Boot, Angular, and databases.
				
				Revise previous topics and clear your doubts.
				
				Solve coding problems and prepare for technical interviews.
				
				Stay focused on your goals and avoid unnecessary distractions.
				
				You do not need to be perfect from day one. Every experienced developer was once a beginner. The important thing is to keep learning, keep practising, and never give up when you face challenges.
				
				Your daily efforts today can create better career opportunities tomorrow. Make the best use of your learning time, participate actively in sessions, and work towards becoming a confident Java Full Stack Developer.
				
				Believe in yourself. Stay disciplined. Practise consistently. Your future is in your hands!
				
				Wishing you all the very best for your learning journey and future careers.
				
				Keep learning, keep growing, and keep coding! 💻🚀
				
				Best regards,
				Velocity Training Team	
					""");

			emailSenderService.sendEmail(job);
		}
	}
}
