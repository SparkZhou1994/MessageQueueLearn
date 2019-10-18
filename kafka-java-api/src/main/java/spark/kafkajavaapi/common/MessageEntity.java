package spark.kafkajavaapi.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName MessageEntity
 * @Description TODO
 * @Author Spark
 * @Date 12/6/2018 2:17 PM
 * @Version 1.0
 **/
@Getter
@Setter
@EqualsAndHashCode
public class MessageEntity {
    private String title;
    private String body;

    @Override
    public String toString() {
        return "MessageEntity{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
