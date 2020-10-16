package com.book.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/*
ActiveMQ  
	ref https://grokonez.com/spring-framework/spring-boot/spring-jms-explicitly-configure-spring-activemq-connectionfactory-springboot#5_Run_and_check_resutls
	after download and unzip, start the activeMq service:
	C:\Users\Huijie\Documents\activemq-5.16.0\bin>activemq start, then view console from localhost:
	http://127.0.0.1:8161/admin/ or http://localhost:8161
	http://localhost:8080/bookjms, post get result list, which is added from the session.
    {
        "bookid": 982,
        "bookname": "Programming with Java",
        "author": "E. Balagurusamy",
        "price": 350
    }
 */
@Configuration
public class ActiveMqConnectionFactoryConfig {
	  @Value("${gkz.activemq.broker.url}")
	  String brokerUrl;
	  
	  @Value("${gkz.activemq.borker.username}")
	  String userName;
	  
	  @Value("${gkz.activemq.borker.password}")
	  String password;
	  
	  @Bean
	  public ConnectionFactory connectionFactory(){
	        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
	        connectionFactory.setBrokerURL(brokerUrl);
	        connectionFactory.setUserName(userName);
	        connectionFactory.setPassword(password);
	        return connectionFactory;
	    }
	    
	  @Bean // Serialize message content to json using TextMessage
	  public MessageConverter jacksonJmsMessageConverter() {
	      MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
	      converter.setTargetType(MessageType.TEXT);
	      converter.setTypeIdPropertyName("_type");
	      return converter;
	  }
	   /*
	     * Used for Receiving Message
     */
    @Bean
    public JmsListenerContainerFactory<?> jsaFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setMessageConverter(jacksonJmsMessageConverter());
        configurer.configure(factory, connectionFactory);
        return factory;
    }
 
    /*
     * Used for Sending Messages.
     */
    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setMessageConverter(jacksonJmsMessageConverter());
        template.setConnectionFactory(connectionFactory());
        return template;
    }	  
}
