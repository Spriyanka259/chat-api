package com.guild.chatapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;
import com.guild.chatapi.controller.ChatController;
import com.guild.chatapi.model.ChatMessage;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ChatControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ChatController chatController;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testSendMessage() throws Exception {
		DateTimeFormatter formatter = new DateTimeFormatterBuilder()
		        .appendPattern("yyyy-MM-dd[ HH:mm:ss]")
		        .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
		        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
		        .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
		        .toFormatter();
		
		ChatMessage mockChatMessage = new ChatMessage();
		mockChatMessage.setContent("hi");
		mockChatMessage.setSender("A");
		mockChatMessage.setRecipient("B");
		LocalDateTime.parse("2022-01-17", formatter);
		mockChatMessage.setCreateDateTime(LocalDateTime.parse("2022-01-17", formatter));

		String inputJson = new Gson().toJson(mockChatMessage);
		when(chatController.sendMessage(mockChatMessage)).thenReturn("message sent successfully");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/message")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		int status = result.getResponse().getStatus();
		assertEquals(200, status);

		assertEquals(result.getResponse().getContentAsString(), "message sent successfully");
	}

	@Test
	void testGetRecentMessages() throws Exception {
		List<ChatMessage> chatMessageList = new ArrayList<>();
		ChatMessage mockChatMessage = new ChatMessage();
		mockChatMessage.setContent("hi");
		mockChatMessage.setSender("A");
		mockChatMessage.setRecipient("B");

		chatMessageList.add(mockChatMessage);

		Mockito.when(chatController.getRecentMessages("", "")).thenReturn(chatMessageList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/messages/from/A/to/B");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		int status = result.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	void testGetAllMessages() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/messages/all");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		int status = result.getResponse().getStatus();
		assertEquals(200, status);
	}

}
