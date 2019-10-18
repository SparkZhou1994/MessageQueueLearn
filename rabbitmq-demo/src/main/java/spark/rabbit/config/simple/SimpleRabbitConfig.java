package spark.rabbit.config.simple;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RabbitConfig
 * @Description TODO
 * @Author Spark
 * @Date 10/18/2019 2:08 PM
 **/
@Configuration
public class SimpleRabbitConfig {

    @Bean
    public Queue simpleQueue() {
        return new Queue("demo-queues");
    }
}
