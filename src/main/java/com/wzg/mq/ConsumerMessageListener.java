package com.wzg.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.wzg.service.IDoMainService;

@Component
public class ConsumerMessageListener implements IMsgListener  {
	private static final Logger log = LoggerFactory.getLogger(ConsumerMessageListener.class);
	@Autowired
	private IDoMainService doMainService;
	@JmsListener(destination="bank1", containerFactory="jmsListenerContainer")
	public void onMesg(Message message, Session session) {
		try {
			log.info("是否重发?");
			log.info(message.getJMSRedelivered()?"是":"否");
		} catch (JMSException e2) {
			e2.printStackTrace();
		}
		TextMessage textMsg = (TextMessage) message;  
		String str = null;
		try {
			str = textMsg.getText();
		} catch (JMSException e1) {
			e1.printStackTrace();
		}
		this.doMain(str);
	}
	
	public void doMain(String str) {
		doMainService.saveZfb(str);
//		System.out.println(1/0);
	}
}
