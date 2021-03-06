# chat-api
Simple implementation of Rest API using Spring Boot, Hibernate and Maven for chatting where:
* A short text message can be sent from one user (the sender) to another (the recipient).
* Recent messages can be requested for a recipient from a specific sender
    * By default, only messages from the last 30 days should be returned. Additionally, there should be a limit of 100 messages in a response.
* Recent messages can be requested from all senders 
    * By default, only messages from the last 30 days should be returned. Additionally, there should be a limit of 100 messages in a response.

# Environment SetUp on Mac
## Install Java
* brew install java
* sudo ln -sfn /usr/local/opt/openjdk/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk.jdk
* java -version (To check if Java version 14 or above is installed)

## Install Maven
* brew install maven
* echo 'export MAVEN_HOME=/usr/local/Cellar/maven/3.6.3_1' >> ~/.bash_profile
* echo 'export PATH=$MAVEN_HOME/bin:$PATH' >> ~/.bash_profile
* mvn -v (To check if maven 3.8.1 is installed)

## Install MySQL
* brew install mysql
* mysql -uroot
* create database chatapi;

## Pre-requisites
* Make sure Java 14 or above, Maven 3.8.1 and MySQL is installed properly before running the service
* Make sure ```chatapi``` database is created properly

## Steps
* git clone https://github.com/Spriyanka259/chat-api.git
* mvn clean install
* mvn spring-boot:run

```bash
API must be up and running at http://localhost:8080/
```

## Test in Postman
* POST http://localhost:8080/message 
    *  Request Body: {
    "sender": "John",
    "recipient": "Doe",
    "content": "Hello"
}
* GET http://localhost:8080/messages/from/{Sender}/to/{Recipient}
    *  http://localhost:8080/messages/from/John/to/Doe
* GET http://localhost:8080/messages/all
* API Documentation [link](https://documenter.getpostman.com/view/15553468/UVXnFtpd)
