package spark.rabbit.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName DirectMessageReceiver
 * @Description TODO
 * @Author Spark
 * @Date 10/18/2019 3:11 PM
 **/
@Component
@RabbitListener(queues = "demo-direct-queue")
public class DirectMessageReceiver {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("Direct Receiver:" + testMessage.toString());
    }
}
