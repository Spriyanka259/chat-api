package com.guild.chatapi.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * model
 * 
 * @author priyankasirigadde
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class ChatMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotEmpty(message = "Sender must not be empty")
	private String sender;
	@NotEmpty(message = "Recipient must not be empty")
	private String recipient;
	@NotEmpty(message = "content must not be empty")
	private String content;
	@CreationTimestamp
	private LocalDateTime createDateTime;
}
