package uz.market.mbozor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MbozorApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(MbozorApplication.class, args);
    }
}
