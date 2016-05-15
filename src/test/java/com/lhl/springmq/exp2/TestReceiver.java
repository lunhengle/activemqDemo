package com.lhl.springmq.exp2;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lunhengle on 2016/2/16.
 */
public class TestReceiver {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Receiver receiver = (Receiver) context.getBean("receiver");
        System.out.println(receiver.receiveMessage());
    }
}
