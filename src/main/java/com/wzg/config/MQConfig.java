package com.wzg.config;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.util.ErrorHandler;
@Configuration
@ImportResource(locations= {"classpath:spring-activemq.xml"})
public class MQConfig {
//	@Resource(name= "jmsTransactionManager")
//	private JmsTransactionManager jmsTransactionManager;

	
	@Bean(name= {"jmsListenerContainer"})
	public DefaultJmsListenerContainerFactory jmsListenerContainer(ConnectionFactory jmsConnectionFactory) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(jmsConnectionFactory);
		factory.setPubSubDomain(false);
		factory.setSessionTransacted(true);
//		factory.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
//		factory.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
//		factory.setTransactionManager(jmsTransactionManager);
		return factory;
    }
	

//    @Bean
//    public Topic topic() {
//        return new ActiveMQTopic("sample.topic");
//	    }
}
