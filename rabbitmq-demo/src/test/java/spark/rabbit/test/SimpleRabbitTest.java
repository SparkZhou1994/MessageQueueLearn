package spark.rabbit.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spark.rabbit.simple.SimpleMessageReceiver;
import spark.rabbit.simple.SimpleMessageSender;

/**
 * @ClassName SimpleRabbitTest
 * @Description TODO
 * @Author Spark
 * @Date 10/18/2019 2:17 PM
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleRabbitTest {

    @Autowired
    private SimpleMessageSender simpleMessageSender;

    @Autowired
    private SimpleMessageReceiver simpleMessageReceiver;

    @Test
    public void receiveSimpleMessage(){
        simpleMessageSender.send();
    }
}
