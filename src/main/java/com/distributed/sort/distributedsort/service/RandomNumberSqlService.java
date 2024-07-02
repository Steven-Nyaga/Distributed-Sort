package com.distributed.sort.distributedsort.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
	
	public List<Integer> randomNumberGenerator(int numberesToGenerate) {
		Random random = new Random();
		List<Integer> randomNumbers = new ArrayList<>();
		for (int i = 1; i <= numberesToGenerate; i++) {
			// Obtain a number between [0 - 10000].
			randomNumbers.add(random.nextInt(1000001));
		}
		
		Collections.sort(randomNumbers, Collections.reverseOrder());
		
		for (int i : randomNumbers) {
			System.out.println("################ Numbers: " + i);
		}
		
		return randomNumbers;
	}

//	public List<Integer> randomNumberGenerator(int numberesToGenerate) {
//		Random random = new Random();
//		List<Integer> randomNumbers = new ArrayList<>();
//		for (int i = 1; i <= numberesToGenerate; i++) {
//			// Obtain a number between [0 - 100].
//			randomNumbers.add(random.nextInt(100));
//		}
//		
//		addAndSortRandomNumber(randomNumbers);
//		
//		
//		Collections.sort(randomNumbers, Collections.reverseOrder());
//		
//		return randomNumbers;
//	}

	public void addAndSortRandomNumber(List<Integer> randomNumbers) {
		// Retrieve all random numbers, sort them, and refill the database
		List<RandomNumber> sortedRandomNumbers = getAllRandomNumbersFromDb();
		
		for (int num : randomNumbers) {
			// Add the new random number to the database
			RandomNumber newRandomNumber = new RandomNumber();
			newRandomNumber.setRandomNumber(num);
			//repository.save(newRandomNumber);
			sortedRandomNumbers.add(newRandomNumber);
		}

		sortNumbersDesc(sortedRandomNumbers);
		refillDatabase(sortedRandomNumbers);
	}

	public List<RandomNumber> getAllRandomNumbersFromDb() {
		List<RandomNumber> randomNumbers = repository.findAll();
		return randomNumbers;
	}
	
	public List<RandomNumber> sortNumbersDesc(List<RandomNumber> randomNumbers) {
		Collections.sort(randomNumbers, (a, b) -> b.getRandomNumber() - a.getRandomNumber());
		return randomNumbers;
	}

	public void refillDatabase(List<RandomNumber> sortedRandomNumbers) {
		repository.deleteAll();
		repository.saveAll(sortedRandomNumbers);
	}
}
