package spark.kafkajavaapi.common;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName Response
 * @Description TODO
 * @Author Spark
 * @Date 12/6/2018 2:19 PM
 * @Version 1.0
 **/
@Getter
@Setter
public class Response {
    private int code;
    private String message;

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
