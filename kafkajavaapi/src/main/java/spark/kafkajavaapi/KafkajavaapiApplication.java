package spark.kafkajavaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class KafkajavaapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkajavaapiApplication.class, args);
    }
}
