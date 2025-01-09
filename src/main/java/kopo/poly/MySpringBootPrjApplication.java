package kopo.poly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MySpringBootPrjApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootPrjApplication.class, args);
    }

}
