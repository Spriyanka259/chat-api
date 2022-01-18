# chat-api
Simple implementation of Rest API using Spring Boot, Hibernate and Maven for chatting including:
* A short text message can be sent from one user (the sender) to another (the recipient).
* Recent messages can be requested for a recipient from a specific sender
    * By default, only messages from the last 30 days should be returned. Additionally, there should be a limit of 100 messages in a response.
* Recent messages can be requested from all senders 
    * By default, only messages from the last 30 days should be returned. Additionally, there should be a limit of 100 messages in a response.
