package com.book.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.book.entity.Books;

@Component
public class JmsProducer {
  @Autowired
  JmsTemplate jmsTemplate;
  
  @Value("${gkz.activemq.queue}")
  String queue;
  
  public void send(Books book){
    jmsTemplate.convertAndSend(queue, book);
  }
}