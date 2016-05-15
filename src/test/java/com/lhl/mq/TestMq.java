package com.lhl.mq;

import com.lhl.mq.Consumer;
import com.lhl.mq.Consumer1;
import com.lhl.mq.Publisher;
import org.apache.activemq.ActiveMQConnection;
import org.junit.Test;

import javax.jms.JMSException;

/**
 * Created by lunhengle on 2016/2/15.
 */
public class TestMq {
    @Test
    public void testPublisher() throws JMSException, InterruptedException {
        System.out.println(ActiveMQConnection.DEFAULT_BROKER_URL + "------------");
        Consumer consumer = new Consumer();
        Consumer1 consumer1 = new Consumer1();
        Publisher publisher = new Publisher();

        System.out.println("consumer start!");
        consumer.consumerMessage();

        System.out.println("consumer1 start!");
        consumer1.consumerMessage();

        publisher.produceMessage("hello word!");
        publisher.close();
        consumer.close();
        consumer1.close();
    }

}
