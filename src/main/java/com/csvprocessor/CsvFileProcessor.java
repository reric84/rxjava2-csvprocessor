package com.csvprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

@Component
public class CsvFileProcessor {

	private static final Logger log = LoggerFactory.getLogger(CsvFileProcessor.class);

	private int chunkSize = 100;
	
	@Autowired
	private DaoServiceImpl daoServiceImpl;
	
	

	public void processFile(String filePath) {
		
		/*final Observable<List<Person>> observable = Observable.defer(() -> new CsvFileObservableSource(filePath))
				.filter(ValidationProcessor::process)
				.map(FirstNameProcessor::process)
				.map(LastNameProcessor::process)
				.map(AgeProcessor::process)
				.buffer(chunkSize);
		 

		
		observable.subscribe(
			chunkData -> System.out.println(chunkData.size()), 
			Throwable::printStackTrace, () -> {
			log.info("File Processing Completed");
			executor.shutdown();
		});
*/		 
		//Observable<GroupedObservable<String, Person>> groupObservable
		 Observable<GroupedObservable<String, Person>> groupObservable = Observable
				.defer(() -> new CsvFileObservableSource(filePath))
				.filter(ValidationProcessor::process)
				.map(FirstNameProcessor::process)
				.map(LastNameProcessor::process)
				.map(AgeProcessor::process)
				.groupBy(Person::getAgeGroup);
				

		groupObservable.subscribe(s -> {
			if ("TEENAGER".equals(s.getKey())) {
				s.buffer(chunkSize).subscribe(daoServiceImpl::insertTeenagerList);
			}
			if ("YOUNGADULT".equals(s.getKey())) {
				s.buffer(chunkSize).subscribe(daoServiceImpl::insertYoungadultList);
			}
			if ("SENIORCITIZENS".equals(s.getKey())) {
				s.buffer(chunkSize).subscribe(daoServiceImpl::insertSeniorcitizensList);
			}
		}, Throwable::printStackTrace, () -> {
			System.out.println("File Processing Complited");
		});
		

	}

}
