package spark.kafkajavaapi.consumer;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import spark.kafkajavaapi.common.MessageEntity;

/**
 * @ClassName SimpleConsumer
 * @Description TODO
 * @Author Spark
 * @Date 12/6/2018 2:34 PM
 * @Version 1.0
 **/
@Slf4j
@Component
public class SimpleConsumer {
    private final Gson gson = new Gson();
    private final static Logger log = LoggerFactory.getLogger(SimpleConsumer.class);

    @KafkaListener(topics = "${kafka.topic.default}", containerFactory = "kafkaListenerContainerFactory")
    public void receive(MessageEntity message) {
        log.error(gson.toJson(message));
    }
}