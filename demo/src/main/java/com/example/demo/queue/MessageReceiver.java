package com.example.demo.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author lishunli
 * @create 2017-11-14 12:25
 **/
@Service
public class MessageReceiver {

    @RabbitListener(queues = AmqpConfig.QUEUE_NAME)
    public void receiveMessage(String message) {

        System.out.println("Received <" + message + ">");
    }
}
