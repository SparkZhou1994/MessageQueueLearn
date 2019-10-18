package spark.rabbit.simple;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName MessageSender
 * @Description TODO
 * @Author Spark
 * @Date 10/18/2019 2:14 PM
 **/
@Component
public class SimpleMessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        String context = "Hello Rabbit" + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("demo-queues", context);
    }
}
