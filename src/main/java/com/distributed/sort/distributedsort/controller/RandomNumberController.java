package com.distributed.sort.distributedsort.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.distributed.sort.distributedsort.models.RandomNumber;

@RestController
public class RandomNumberController {
	
	@PostMapping("/distributed-sort")
	public String saveValue(@RequestBody RandomNumber randomNumber) {
		try {
			
			
			return "Success -> Check DB";
			
		} catch (Exception e) {
			
			System.out.println(e);
			return "Error";
		}
	}
	
}
