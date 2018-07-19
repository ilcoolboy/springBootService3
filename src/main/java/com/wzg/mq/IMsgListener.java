package com.wzg.mq;

import javax.jms.Message;
import javax.jms.Session;

public interface IMsgListener {
	void onMesg(Message message, Session session);
}
