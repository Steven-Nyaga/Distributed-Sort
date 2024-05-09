package com.distributed.sort.distributedsort.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.distributed.sort.distributedsort.models.NumberRequestBody;
import com.distributed.sort.distributedsort.models.RandomNumber;
import com.distributed.sort.distributedsort.service.RandomNumberSqlService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RandomNumberController {
	
	private final RabbitTemplate rabbitTemplate;
	
	@Autowired
	RandomNumberSqlService randomNumberSqlService;
	
	public RandomNumberController(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}

	@PostMapping("/distributed-sort")
	public String saveValue(@RequestBody NumberRequestBody requestBody) {
		try {
			
			randomNumberSqlService.randomNumberGenerator(requestBody.getRandomNumberCount());
			// ObjectMapper objectMapper = new ObjectMapper();
			// String json = objectMapper.writeValueAsString(randomNumber);
			// rabbitTemplate.convertAndSend("", "random-number", json);
			return "Success -> Check DB";
		} catch (Exception e) {
			System.out.println(e);
			return "Error";
		}
	}
	
    @GetMapping("/data")
    public Page<RandomNumber> getAllData(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "50") int size) {
    	
        try {
        	
			Pageable pageable = PageRequest.of(page, size);
			return randomNumberSqlService.getAllData(pageable);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
    }
	
}
