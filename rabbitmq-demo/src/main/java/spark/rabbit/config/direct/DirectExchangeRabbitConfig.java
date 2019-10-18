package spark.rabbit.config.direct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName DirectExchangeRabbitConfig
 * @Description TODO
 * @Author Spark
 * @Date 10/18/2019 2:54 PM
 **/
/*@Configuration*/
public class DirectExchangeRabbitConfig {

    @Bean
    public Queue demoDirectQueue(){
        return new Queue("demo-direct-queue",true);
    }

    @Bean
    public DirectExchange demoDirectExchange(){
        return new DirectExchange("demo-direct-exchange");
    }

    @Bean
    public Binding bindingDirect(){
        return BindingBuilder.bind(demoDirectQueue()).to(demoDirectExchange()).with("demoDirectRouting");
    }
}
