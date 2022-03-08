package com.greedy.waterfall.common.mail;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/servlet-context.xml",
		"classpath:spring/root-context.xml",
		"classpath:mybatis/mybatis-config.xml"
})
public class EmailSenderImplTest {

	@Autowired private EmailSender emailSender;
	
	@Test
	public void emailSenderTest() {
		emailSender.emailSender("text message....");
	}

}
