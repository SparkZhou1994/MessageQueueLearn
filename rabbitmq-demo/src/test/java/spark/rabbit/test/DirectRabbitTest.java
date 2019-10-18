package spark.rabbit.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spark.rabbit.direct.DirectMessageReceiver;
import spark.rabbit.direct.DirectMessageSender;

/**
 * @ClassName DirectRabbitTest
 * @Description TODO
 * @Author Spark
 * @Date 10/18/2019 3:53 PM
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DirectRabbitTest {

    @Autowired
    private DirectMessageSender directMessageSender;

    @Autowired
    private DirectMessageReceiver directMessageReceiver;

    @Test
    public void receiveDirectExchangeMessage(){
        directMessageSender.send();
    }
}
