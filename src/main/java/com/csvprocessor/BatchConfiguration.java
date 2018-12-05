package com.csvprocessor;

import javax.annotation.PostConstruct;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BatchConfiguration {

	@PostConstruct
	public void getDbManager() {
		System.setProperty("java.awt.headless", "false");
		
		DatabaseManagerSwing.main(
				new String[] { "--url", "jdbc:hsqldb:mem:db/test", "--user", "sa", "--password", "" });
	}

}
