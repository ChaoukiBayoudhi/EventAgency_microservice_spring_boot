package tn.esb.siad.eventAgency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EventAgencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventAgencyApplication.class, args);
	}

}
