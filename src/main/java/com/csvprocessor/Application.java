package com.csvprocessor;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(CsvFileProcessor.class);

	public static void main(String[] args) { 
		
		ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);		
		CsvFileProcessor logFileProcessor = run.getBean(CsvFileProcessor.class);
		Instant start = Instant.now();	
		logFileProcessor.processFile(System.getProperty("user.dir") + "/src/main/resources/sample-data.csv");
		
		Instant end = Instant.now();		
		
		log.info("Time Taken to Process File {} Minutes",java.time.Duration.between(end, start).toMinutes());
	}
}
