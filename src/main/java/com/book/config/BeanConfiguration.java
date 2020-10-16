package com.book.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.book.jms.MessageStorage;

@Configuration
public class BeanConfiguration {
	  @Bean
	  public MessageStorage bookStorage() {
	    return new MessageStorage();
	  }
}
