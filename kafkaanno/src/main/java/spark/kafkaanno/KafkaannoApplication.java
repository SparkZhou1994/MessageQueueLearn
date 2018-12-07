package spark.kafkaanno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import spark.kafkaanno.producer.KafkaSender;

@SpringBootApplication
@EnableScheduling
public class KafkaannoApplication {
    @Autowired
    private KafkaSender kafkaSender;

    public static void main(String[] args) {
        SpringApplication.run(KafkaannoApplication.class, args);
    }

    @Scheduled(fixedRate =  1000*60)
    public void testKafka() throws Exception {
        kafkaSender.sendTest();
    }
}
