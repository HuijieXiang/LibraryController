package com.book.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.book.entity.Books;

@Component
public class JmsConsumer {
  @Autowired
  private MessageStorage bookStorage;
  
  @JmsListener(destination = "${gkz.activemq.queue}", containerFactory="jsaFactory")
  public void receive(Books book){
    System.out.println("Recieved Message: " + book);
    bookStorage.add(book);
  }

}
