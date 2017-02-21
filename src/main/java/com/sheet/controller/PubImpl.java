package com.sheet.controller;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

public class PubImpl {
//	Logger LOGGER = Logger.getLogger("PUBIMPL");
	@Autowired
	SheetController  mSheetController;
	@Autowired
	StringRedisTemplate template;
	@Autowired
	RedisConnectionFactory connectionFactory;
	@Autowired
	MessageListenerAdapter listenerAdapter;
	@Autowired
	CountDownLatch latch;

	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {

		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(listenerAdapter, new PatternTopic("chat"));

		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(SubImpl receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	@Bean
	SubImpl receiver(CountDownLatch latch) {
		return new SubImpl(latch);
	}

	@Bean
	CountDownLatch latch() {
		return new CountDownLatch(1);
	}

	@Bean
	StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
		return new StringRedisTemplate(connectionFactory);
	}
	
	 // Publisher method
	 
	public void sendMessage() throws Exception {
        System.out.println("Publisher Method Running....");
        System.out.println("Storing sheet info to Db......");
//		LOGGER.info("Sending message...");
		mSheetController.getSheetData(); 
		System.out.println(" updated to Db");
		template.convertAndSend("chat", "sheet data updated to Db");
		latch.await();

	}
}
