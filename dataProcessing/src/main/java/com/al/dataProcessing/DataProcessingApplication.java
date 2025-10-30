package com.al.dataProcessing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class DataProcessingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataProcessingApplication.class, args);
	}

}
