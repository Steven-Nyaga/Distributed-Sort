package com.distributed.sort.distributedsort.models;

import com.google.cloud.Timestamp;

public class RandomNumber {
	
	private int randomNumber;
	private Timestamp timestamp;
	
	public int getRandomNumber() {
		return randomNumber;
	}
	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
}
