package spark.rabbit.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName TopicDemoMessageReceiver
 * @Description TODO
 * @Author Spark
 * @Date 10/18/2019 4:27 PM
 **/

@Component
@RabbitListener(queues = "topic.demo")
public class TopicDemoMessageReceiver {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("TopicDemoReceiver:" + testMessage.toString());
    }
}
