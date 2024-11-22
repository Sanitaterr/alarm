package com.jzy.alarmsystembackend.util.testbean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author JZY
 * @version 1.0
 * Create by 2024/11/22 12:15
 * @Description: TODO
 */
@Slf4j
@Component
public class MqListener {

    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueue(String msg) {
        System.out.println("消费者收到了simple.queue的消息：[" + msg + "]");
    }

    @RabbitListener(queues = "work.queue")
    public void listenWorkQueue1(String msg) throws InterruptedException {
        System.out.println("消费者1收到了work.queue的消息：[" + msg + "]");
        Thread.sleep(20);
    }

    @RabbitListener(queues = "work.queue")
    public void listenWorkQueue2(String msg) throws InterruptedException {
        System.err.println("消费者2收到了work.queue的消息：[" + msg + "]");
        Thread.sleep(200);
    }
}
