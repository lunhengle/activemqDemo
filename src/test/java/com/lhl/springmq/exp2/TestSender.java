package com.lhl.springmq.exp2;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lunhengle on 2016/2/16.
 */
public class TestSender {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Sender sender = (Sender) context.getBean("sender");
        sender.sendInfo();
    }
}
