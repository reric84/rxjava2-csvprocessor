package com.csvprocessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;

/**
 * 
 * @author Niraj Sonawane
 *
 */
public class CsvFileObservableSource implements ObservableSource<Person> {

	private static final Logger log = LoggerFactory.getLogger(CsvFileObservableSource.class);
	private final String filename;

	CsvFileObservableSource(String filename) {
		this.filename = filename; 
	}

	@Override
	public void subscribe(Observer<? super Person> observer) {
		try {
			Files.lines(Paths.get(filename)).forEach(inputLine -> {
				String[] split = inputLine.split(",");
				
				observer.onNext(Person.builder().firstName(split[0]).lastName(split[1]).age(Integer.parseInt(split[2]))
						.build());
			});
			observer.onComplete();
		} catch (IOException e) {
			observer.onError(e);
		}
	}

}
