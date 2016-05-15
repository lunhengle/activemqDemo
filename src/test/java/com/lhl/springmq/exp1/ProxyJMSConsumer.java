package com.lhl.springmq.exp1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * Created by lunhengle on 2016/2/15.
 */
public class ProxyJMSConsumer {
    public ProxyJMSConsumer() {

    }

    private JmsTemplate jmsTemplate;

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     *
     */
    public void recive() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-jms.xml");
        Destination destination = (Destination) applicationContext.getBean("destination");
        while (true) {
            TextMessage txtmsg = (TextMessage) jmsTemplate.receive(destination);
            if (null != txtmsg) {
                System.out.println("[DB Proxy] " + txtmsg);
                try {
                    System.out.println("[DB Proxy] 收到消息内容为: "
                            + txtmsg.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }
        }
    }
}
