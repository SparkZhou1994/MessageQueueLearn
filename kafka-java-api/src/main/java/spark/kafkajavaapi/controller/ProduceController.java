package spark.kafkajavaapi.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spark.kafkajavaapi.common.ErrorCode;
import spark.kafkajavaapi.common.MessageEntity;
import spark.kafkajavaapi.common.Response;
import spark.kafkajavaapi.consumer.SimpleConsumer;
import spark.kafkajavaapi.producer.SimpleProducer;

/**
 * @ClassName ProduceController
 * @Description TODO
 * @Author Spark
 * @Date 12/6/2018 2:36 PM
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/kafka")
public class ProduceController {
    @Autowired
    private SimpleProducer simpleProducer;
    @Value("${kafka.topic.default}")
    private String topic;
    private Gson gson = new Gson();
    private final static Logger log = LoggerFactory.getLogger(ProduceController.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = {"application/json"})
    public Response sendKafka() {
        return new Response(ErrorCode.SUCCESS, "OK");
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST, produces = {"application/json"})
    public Response sendKafka(@RequestBody MessageEntity message) {
        try {
            log.info("kafka的消息={}", gson.toJson(message));
            simpleProducer.send(topic, "key", message);
            log.info("发送kafka成功.");
            return new Response(ErrorCode.SUCCESS, "发送kafka成功");
        } catch (Exception e) {
            log.error("发送kafka失败", e);
            return new Response(ErrorCode.EXCEPTION, "发送kafka失败");
        }
    }
}
