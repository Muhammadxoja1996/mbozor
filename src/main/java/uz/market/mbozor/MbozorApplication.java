package uz.market.mbozor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MbozorApplication {

	public static void main(String[] args) {
		ApplicationContext c = SpringApplication.run(MbozorApplication.class, args);
	}

}
