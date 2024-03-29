package com.distributed.sort.distributedsort.service;

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

	public void addNewNumbers(RandomNumber newNumber) {
		
	}
	
	public Page<RandomNumber> getAllData(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
