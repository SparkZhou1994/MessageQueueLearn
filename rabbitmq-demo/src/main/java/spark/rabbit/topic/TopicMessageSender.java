package spark.rabbit.topic;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spark.rabbit.config.topic.TopicRabbitConfig;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName TopicMessageSender
 * @Description TODO
 * @Author Spark
 * @Date 10/18/2019 4:22 PM
 **/
@Component
public class TopicMessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendDemo() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "Demo Top Message";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> manMap = new HashMap<>();
        manMap.put("messageId", messageId);
        manMap.put("messageData", messageData);
        manMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicExchange", TopicRabbitConfig.TOPIC_DEMO, manMap);
    }

    public void sendTest() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "Demo Top Message";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> manMap = new HashMap<>();
        manMap.put("messageId", messageId);
        manMap.put("messageData", messageData);
        manMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicExchange", TopicRabbitConfig.TOPIC_TEST, manMap);
    }
}
