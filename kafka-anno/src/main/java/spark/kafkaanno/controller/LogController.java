package spark.kafkaanno.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName LogController
 * @Description TODO
 * @Author Spark
 * @Date 11/30/2018 4:33 PM
 * @Version 1.0
 **/
@Controller
public class LogController {
    private final static Logger logger = LoggerFactory.getLogger(LogController.class);

    @GetMapping("/logs")
    @ResponseBody
    public String logs() {
        String message = "controller:logs";
        logger.error(message);
        System.out.println(message);
        return message;
    }
}

