package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Created by IntelliJ IDEA.
 * User: zmq
 * Date: 2019/12/30
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class LeyouSmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeyouSmsApplication.class);
    }
}
