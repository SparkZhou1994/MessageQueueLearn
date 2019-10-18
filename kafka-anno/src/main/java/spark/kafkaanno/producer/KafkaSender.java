package spark.kafkaanno.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @ClassName KafkaSender
 * @Description TODO
 * @Author Spark
 * @Date 11/30/2018 3:13 PM
 * @Version 1.0
 **/
@Component
public class KafkaSender {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendTest() {
        kafkaTemplate.send("test","hello,kafka "
                + LocalDateTime.now());
    }
}

