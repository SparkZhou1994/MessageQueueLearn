package spark.kafkaanno.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName KafkaConsumer
 * @Description TODO
 * @Author Spark
 * @Date 11/30/2018 3:16 PM
 * @Version 1.0
 **/
@Component
public class KafkaConsumer {
    private final static Logger logger= LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = {"test"})
    public void consumer(String message) {
        logger.error(message);
    }
}
