package com.lhl.springmq.exp1;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by lunhengle on 2016/2/15.
 * 发送端
 * 先启动接收端 然后启动发送端
 */
public class TestSender {

    @Test
    public void test(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-jms.xml");
        JmsTemplate jmsTemplate = (JmsTemplate) applicationContext.getBean("jmsTemplate");
        Destination destination = (Destination) applicationContext.getBean("destination");
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("发送消息： Hello ActiveMQ Text Message!");
            }
        });
        System.out.println("成功发送了一条消息！");
    }
}
