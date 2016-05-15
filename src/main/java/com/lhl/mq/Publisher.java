package com.lhl.mq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by lunhengle on 2016/2/15.
 * activemq 生产者类
 */
public class Publisher {
    //中间件用户
    private String username = ActiveMQConnection.DEFAULT_USER;
    //中间件密码
    private String password = ActiveMQConnection.DEFAULT_PASSWORD;
    //中间件路径
    private String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    //中间件主题
    private String subject = "hello";
    //目的地
    private Destination destination = null;
    //连接
    private Connection connection = null;
    //会话
    private Session session = null;
    //信息生产者
    private MessageProducer producer = null;

    //初始化
    private void initialize() throws JMSException {
        //创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(username, password, url);
        //创建连接
        connection = activeMQConnectionFactory.createConnection();
        /**
         * 创建会话
         *  paramA 设置为true时表示启用事务 false 表示不启用事务
         *  参数 paramA 设置为false时paramB的值可为Session.AUTO_ACKNOWLEDGE，Session.CLIENT_ACKNOWLEDGE，DUPS_OK_ACKNOWLEDGE其中一个
         *  参数 paramA 设置为true时 paramB的值忽略 acknowledgment mode被jms服务器设置为SESSION_TRANSACTED
         *  Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收消息不需要做额外的工作。
         *  Session.CLIENT_ACKNOWLEDGE为客户端确认。客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法。jms服务器才会删除消息。
         *  DUPS_OK_ACKNOWLEDGE允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。在需要考虑资源使用时，这种模式非常有效。
         *  · AUTO_ACKNOWLEDGE = 1    自动确认
         *　当会话从对 receive 的调用成功返回时，或在会话已调用的用于处理消息的消息侦听器成功返回时，会话会自动确认客户端的消息接收。
         *  ·  CLIENT_ACKNOWLEDGE = 2    客户端手动确认
         *　　通过此确认模式，客户端通过调用消息的 acknowledge 方法确认已使用的消息。 确认已使用的消息将确认该会话已使用的所有消息。
         *　·  DUPS_OK_ACKNOWLEDGE = 3    自动批量确认
         *　此确认模式指示会话延迟确认消息的传送。这可能在 JMS 提供者失败的情况下导致传送某些重复消息，因此只有能允许重复消息的使用方才应使用此模式。使用此模式可以通过最大限度地减少会话为防止重复所做的工作，从而减少会话开销。
         *　　·  SESSION_TRANSACTED = 0    事务提交并确认
         *　如果会话是事务的则使用此模式，忽略设置的其他模式值
         *  在事务开启之后，和session.commit()之前，所有消费的消息，要么全部正常确认，要么全部redelivery。这种严谨性，通常在基于GROUP(消息分组)或者其他场景下特别适合。
         */
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地
        destination = session.createQueue(subject);//点对点
        //destination =session.createTopic(subject);//发布订阅
        //根据目的地创建生产者
        producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
    }

    //发送消息
    public void produceMessage(String message) throws JMSException {
        initialize();
        //设置消息类型为文本模式
        TextMessage msg = session.createTextMessage(message);
        //连接开始
        connection.start();
        System.out.println("Producer:->sending message start!");
        //发布消息
        producer.send(msg);
        System.out.println("Producer:->sending message end!");
    }

    //关闭连接
    public void close() throws JMSException {
        System.out.println("Producer:->closeing connection!");
        //关闭生产者
        if (null != producer) {
            producer.close();
        }
        //关闭会话
        if (null != session) {
            session.close();
        }
        //关闭连接
        if (null != connection) {
            connection.close();
        }
    }
}
