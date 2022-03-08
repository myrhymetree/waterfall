package com.greedy.waterfall.common.mail;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class Application {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(MailConfig.class);
		
		EmailSender es = context.getBean("email", EmailSenderImpl.class);
		
		es.emailSender("안녕");
		
		
	}
}
