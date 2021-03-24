package qust.travelassistant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("qust.travelassistant.dao")
public class TravelassistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelassistantApplication.class, args);
    }

}
