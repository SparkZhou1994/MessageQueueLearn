package spark.rabbit.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName TopicAllMessageReceiver
 * @Description TODO
 * @Author Spark
 * @Date 10/18/2019 4:28 PM
 **/
@Component
@RabbitListener(queues = "topic.#")
public class TopicAllMessageReceiver {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("TopicAllReceiver:" + testMessage.toString());
    }
}
