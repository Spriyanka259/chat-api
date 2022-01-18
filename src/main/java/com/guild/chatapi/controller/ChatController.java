package com.guild.chatapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.guild.chatapi.model.ChatMessage;
import com.guild.chatapi.repository.ChatMessageRepository;

/**
 * Controller - handles all GET, POST requests used in Chat API
 * 
 * @author priyankasirigadde
 *
 */
@RestController
@Validated
public class ChatController {

	@Autowired
	private ChatMessageRepository chatMessageRepository;

	/**
	 * A short text message can be sent from one user (the sender) to another (the recipient)
	 * 
	 * @param chatMessage
	 * @return String
	 */
	@PostMapping(path = "/message", consumes = { "application/json" })
	public String sendMessage(@RequestBody ChatMessage chatMessage) {
		chatMessageRepository.save(chatMessage);
		return "message sent successfully";
	}
	
	/**
	 * Recent messages can be requested for a recipient from a specific sender
	 * 
	 * @param sender
	 * @param recipient
	 * @return list of messages
	 */
	@GetMapping(path = "/messages/from/{sender}/to/{recipient}")
	public List<ChatMessage> getRecentMessages(@PathVariable String sender,@PathVariable String recipient) {
		return chatMessageRepository.findBySenderAndRecipientByLimit(sender, recipient);		
	}
	
	/**
	 * Recent messages can be requested from all senders
	 * 
	 * @return list of messages
	 */
	@GetMapping(path = "/messages/all")
	public List<ChatMessage> getAllMessages() {
		return chatMessageRepository.findAllByLimit();		
	}
}
