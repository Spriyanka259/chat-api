package com.guild.chatapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.guild.chatapi.model.ChatMessage;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {

	@Query(nativeQuery = true, value = "select * from chat_message where  sender=?1 and recipient=?2 and create_date_time > now() - INTERVAL 30 day order by create_date_time desc limit 100;")
	List<ChatMessage> findBySenderAndRecipientByLimit(String sender, String recipient);
	
	@Query(nativeQuery = true, value = "select * from chat_message where create_date_time > now() - INTERVAL 30 day order by create_date_time desc limit 100;")
	List<ChatMessage> findAllByLimit();
}
