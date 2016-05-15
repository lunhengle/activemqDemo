package com.lhl.springmq.exp2;

import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;
import javax.jms.MapMessage;

/**
 * Created by lunhengle on 2016/2/16.
 */
public class Receiver {
    public Receiver() {

    }

    private JmsTemplate jmsTemplate;

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public String receiveMessage() {
        String str = "";
        MapMessage mapMessage = (MapMessage) jmsTemplate.receive();
        try {
            str = mapMessage.getString("lunhengle");
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return str;
    }
}
