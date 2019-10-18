package spark.rabbit.simple;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName MessageReceiver
 * @Description TODO
 * @Author Spark
 * @Date 10/18/2019 2:16 PM
 **/
@Component
@RabbitListener(queues = "demo-queues")
public class SimpleMessageReceiver {

    @RabbitHandler
    public void receiver(String message){
        System.out.println("Receiver:" + message);
    }
}
