package com.distributed.sort.distributedsort.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.distributed.sort.distributedsort.models.RandomNumber;
import com.distributed.sort.distributedsort.service.RandomNumberService;

@RestController
public class RandomNumberController {

	@Autowired
	private RandomNumberService randomNumberService;
	
	@PostMapping("/distributed-sort")
	public String saveValue(@RequestBody RandomNumber randomNumber) {
		try {
			
			randomNumberService.addNewNumbers(randomNumber.getRandomNumber());
			return "Success -> Check DB";
			
		} catch (Exception e) {
			
			System.out.println(e);
			return "Error";
		}
	}
	
}
