package com.distributed.sort.distributedsort.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import org.springframework.stereotype.Service;

import com.distributed.sort.distributedsort.models.RandomNumber;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class RandomNumberService {

	public void addNewNumbers(int newNumber) {
		Firestore db = FirestoreClient.getFirestore();

		// Retrieve existing numbers from Firestore
		List<RandomNumber> existingNumbers = getAllNumbersFromFirestore(db);

		// Create a PriorityQueue with existing numbers
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

		// priorityQueue.addAll(existingNumbers);
		for (RandomNumber num : existingNumbers) {
			priorityQueue.add(num.getRandomNumber());
		}

		// Add new numbers to the PriorityQueue
		priorityQueue.add(newNumber);

		// Convert the PriorityQueue back to a list
		List<RandomNumber> sortedNumbers = new ArrayList<>();
		while (!priorityQueue.isEmpty()) {
			int val = priorityQueue.poll();
			RandomNumber randomNumber = new RandomNumber();
			randomNumber.setRandomNumber(val);
			sortedNumbers.add(randomNumber);
		}

		// Update Firestore with the sorted numbers
		updateFirestoreWithSortedNumbers(db, sortedNumbers);
	}

	private void updateFirestoreWithSortedNumbers(Firestore db, List<RandomNumber> sortedNumbers) {

		try {
			// Clear existing documents in the collection
			db.collection("distributed_sort").listDocuments().forEach(DocumentReference::delete);

			// Add new sorted numbers
			for (RandomNumber randomNumber : sortedNumbers) {
				db.collection("distributed_sort").add(randomNumber);
			}
		} catch (Exception e) {
			e.printStackTrace(); 
		}

	}

	private List<RandomNumber> getAllNumbersFromFirestore(Firestore db) {

		List<RandomNumber> numbers = new ArrayList<>();
		try {
			ApiFuture<QuerySnapshot> querySnapshot = db.collection("distributed_sort").get();
			for (QueryDocumentSnapshot document : querySnapshot.get().getDocuments()) {
				RandomNumber randomNumber = document.toObject(RandomNumber.class);
				numbers.add(randomNumber);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return numbers;

	}

}
