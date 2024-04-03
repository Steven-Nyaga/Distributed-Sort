package com.distributed.sort.distributedsort.service;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.distributed.sort.distributedsort.models.RandomNumber;
import com.distributed.sort.distributedsort.repositories.RandomNumberRepository;

@Service
public class RandomNumberSqlService {
	
	private final RandomNumberRepository repository;
	
	public RandomNumberSqlService(RandomNumberRepository repository) {
		super();
		this.repository = repository;
	}
	
	public Page<RandomNumber> getAllData(Pageable pageable) {
        return repository.findAll(pageable);
    }

	public void addAndSortRandomNumber(int randomNumber) {
		// Add the new random number to the database
        RandomNumber newRandomNumber = new RandomNumber();
        newRandomNumber.setRandomNumber(randomNumber);
        repository.save(newRandomNumber);
        
     // Retrieve all random numbers, sort them, and refill the database
        List<RandomNumber> sortedRandomNumbers = getAllRandomNumbersSortedDesc();
        refillDatabase(sortedRandomNumbers);
	}
	
	public List<RandomNumber> getAllRandomNumbersSortedDesc() {
        List<RandomNumber> randomNumbers = repository.findAll();
        Collections.sort(randomNumbers, (a, b) -> b.getRandomNumber() - a.getRandomNumber());
        return randomNumbers;
    }

    public void refillDatabase(List<RandomNumber> sortedRandomNumbers) {
    	repository.deleteAll();
    	repository.saveAll(sortedRandomNumbers);
    }
}
