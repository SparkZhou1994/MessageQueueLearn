package spark.rabbit.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spark.rabbit.topic.TopicAllMessageReceiver;
import spark.rabbit.topic.TopicDemoMessageReceiver;
import spark.rabbit.topic.TopicMessageSender;

/**
 * @ClassName TopRabbitTest
 * @Description TODO
 * @Author Spark
 * @Date 10/18/2019 4:30 PM
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopRabbitTest {

    @Autowired
    private TopicMessageSender topicMessageSender;

    @Autowired
    private TopicDemoMessageReceiver topicDemoMessageReceiver;

    @Autowired
    private TopicAllMessageReceiver topicAllMessageReceiver;

    @Test
    public void receiveSimpleMessage(){
        topicMessageSender.sendDemo();
        System.out.println("=============");
        topicMessageSender.sendTest();
    }
}
