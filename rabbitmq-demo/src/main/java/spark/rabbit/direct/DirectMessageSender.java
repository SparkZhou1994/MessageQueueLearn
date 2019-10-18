package spark.rabbit.direct;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName DirectMessageSender
 * @Description TODO
 * @Author Spark
 * @Date 10/18/2019 3:10 PM
 **/
@Component
public class DirectMessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "Hello Direct Exchange!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("demo-direct-exchange", "demoDirectRouting", map);
    }
}
