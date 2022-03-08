package com.greedy.waterfall.common.mail;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@ComponentScan(basePackages = "com.greedy.waterfall.common.mail")
public class MailConfig {
	
	@Bean
	public static JavaMailSender mailSender() {
		JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
		mailSenderImpl.setHost("smtp.gmail.com");
		mailSenderImpl.setPort(587);
		mailSenderImpl.setUsername("luckyclanhong1@gmail.com");
		mailSenderImpl.setPassword("byhcwotbyxtnehvj");
		mailSenderImpl.setDefaultEncoding("UTF-8");
		
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.auth", true);
		javaMailProperties.put("mail.smtp.starttls.enable", true);
		javaMailProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		mailSenderImpl.setJavaMailProperties(javaMailProperties);
		
		return mailSenderImpl;
	}
}