package com.guild.chatapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * Rest API for :
 * sending short text message can be sent from one user (the sender) to another (the recipient),
 * recent messages can be requested for a recipient from a specific sender
 * Recent messages can be requested from all senders
 * By default, only messages from the last 30 days should be returned. Additionally,
 * there should be a limit of 100 messages in a response.
 * 
 * @author priyankasirigadde
 *
 */
@SpringBootApplication
@ComponentScans(value = { @ComponentScan("com.guild.chatapi.repository"), @ComponentScan("com.guild.chatapi.service"),
		@ComponentScan("com.guild.chatapi.model") })
public class ChatApiApplication {

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
	     return new MethodValidationPostProcessor();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ChatApiApplication.class, args);
	}

}
