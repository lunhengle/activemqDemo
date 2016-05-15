package com.lhl.springmq.exp1;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lunhengle on 2016/2/15.
 * 接收端
 * 先启动接收端 然后启动发送端
 */
public class TestProxyJMSConsumer {
    @Test
    public void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-jms.xml");
        ProxyJMSConsumer proxyJMSConsumer = (ProxyJMSConsumer) applicationContext.getBean("proxyJMSConsumer");
        proxyJMSConsumer.recive();
        System.out.println("初始化消息消费者");
    }
}
