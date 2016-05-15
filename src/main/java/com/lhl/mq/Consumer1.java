package com.lhl.mq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by lunhengle on 2016/2/15.
 */
public class Consumer1 implements MessageListener {
    private String username = ActiveMQConnection.DEFAULT_USER;
    private String password = ActiveMQConnection.DEFAULT_PASSWORD;
    private String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private String subject = "hello";
    private Destination destination = null;
    private Connection connection = null;
    private Session session = null;
    private MessageConsumer consumer = null;

    //初始化
    private void initialize() throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(username, password, url);
        connection = activeMQConnectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue(subject);//点对点
        //destination = session.createTopic(subject);//发布订阅
        consumer = session.createConsumer(destination);
    }

    //接受消息
    public void consumerMessage() throws JMSException {
        initialize();
        connection.start();
        consumer.setMessageListener(this);
       /* Message message = consumer.receive();
        receiveMessage(message);*/

        System.out.println("Consumer begin listener!");
    }

    //关闭消息
    public void close() throws JMSException {
        if (null != consumer) {
            consumer.close();
        }
        if (null != session) {
            session.close();
        }
        if (null != connection) {
            connection.close();
        }
    }

    @Override
    public void onMessage(Message message) {
        receiveMessage(message);
    }

    public void receiveMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            String msg = null;
            try {
                msg = textMessage.getText();
            } catch (JMSException e) {
                e.printStackTrace();
            }
            System.out.println("Consumer1:->Received:" + msg);
        } else {
            System.out.println("Consumer1:->Received:" + message);
        }
    }
}
