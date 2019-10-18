package spark.rabbit.config.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName TopicRabbitConfig
 * @Description TODO
 * @Author Spark
 * @Date 10/18/2019 3:37 PM
 **/
/*@Configuration*/
public class TopicRabbitConfig {
    public final static String TOPIC_DEMO = "topic.demo";
    public final static String TOPIC_TEST = "topic.test";

    @Bean
    public Queue topicDemoQueue(){
        return new Queue(TopicRabbitConfig.TOPIC_DEMO);
    }

    @Bean
    public Queue topicTestQueue(){
        return new Queue(TopicRabbitConfig.TOPIC_TEST);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }

    @Bean
    Binding bindingDemoTopicExchange(){
        return BindingBuilder.bind(topicDemoQueue()).to(topicExchange()).with("topic.demo");
    }

    @Bean
    Binding bindingAllTopicExchange(){
        return BindingBuilder.bind(topicDemoQueue()).to(topicExchange()).with("topic.#");
    }
}
