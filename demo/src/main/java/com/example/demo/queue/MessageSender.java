package com.example.demo.queue;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lishunli
 * @create 2017-11-14 12:22
 **/
@Service
public class MessageSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        for (int i = 0; i <10 ; i++) {
            rabbitTemplate.convertAndSend(AmqpConfig.QUEUE_NAME, "测试MQ！！！!");
        }

    }
}
