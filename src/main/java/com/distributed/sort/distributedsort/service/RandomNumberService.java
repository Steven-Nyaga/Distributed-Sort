//package com.distributed.sort.distributedsort.service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.PriorityQueue;
//
//import org.springframework.stereotype.Service;
//
//import com.distributed.sort.distributedsort.models.RandomNumber;
//import com.google.api.core.ApiFuture;
//import com.google.cloud.Timestamp;
//import com.google.cloud.firestore.DocumentReference;
//import com.google.cloud.firestore.Firestore;
//import com.google.cloud.firestore.QueryDocumentSnapshot;
//import com.google.cloud.firestore.QuerySnapshot;
//import com.google.firebase.cloud.FirestoreClient;
//
//@Service
//public class RandomNumberService {
//
//	public void addNewNumbers(RandomNumber newNumber) {
//		Firestore db = FirestoreClient.getFirestore();
//
//		// Retrieve existing numbers from Firestore
//		List<RandomNumber> existingNumbers = getAllNumbersFromFirestore(db);
//
//		// Add new number to existing numbers
//		existingNumbers.add(newNumber);
//
//		// Sort the numbers in descending order
//		List<RandomNumber> sortedNumbers = sortNumbers(existingNumbers);
//
//		// Update Firestore with the sorted numbers
//		updateFirestoreWithSortedNumbers(db, sortedNumbers);
//	}
//
//	private List<RandomNumber> sortNumbers(List<RandomNumber> existingNumbers) {
//
//		// Create a PriorityQueue (MaxHeap) with existing numbers
//		PriorityQueue<RandomNumber> priorityQueue = new PriorityQueue<>(
//				(a, b) -> b.getRandomNumber() - a.getRandomNumber());
//
//		// Add existing numbers to the PriorityQueue
//		priorityQueue.addAll(existingNumbers);
//
//		// Convert the PriorityQueue back to a list
//		List<RandomNumber> sortedNumbers = new ArrayList<>();
//		while (!priorityQueue.isEmpty()) {
//			sortedNumbers.add(priorityQueue.poll());
//		}
//
//		return sortedNumbers;
//	}
//
//	private void updateFirestoreWithSortedNumbers(Firestore db, List<RandomNumber> sortedNumbers) {
//
//		try {
//			// Clear existing documents in the collection
//			db.collection("distributed_sort").listDocuments().forEach(DocumentReference::delete);
//
//			// Add new sorted numbers
//			for (RandomNumber randomNumber : sortedNumbers) {
//				long currentTimeMillis = System.currentTimeMillis(); 
//		        Timestamp timestamp = Timestamp.ofTimeMicroseconds(currentTimeMillis * 1000);
//				randomNumber.setTimestamp(timestamp);
//				db.collection("distributed_sort").add(randomNumber);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	private List<RandomNumber> getAllNumbersFromFirestore(Firestore db) {
//
//		List<RandomNumber> numbers = new ArrayList<>();
//		try {
//			ApiFuture<QuerySnapshot> querySnapshot = db.collection("distributed_sort").get();
//			for (QueryDocumentSnapshot document : querySnapshot.get().getDocuments()) {
//				RandomNumber randomNumber = document.toObject(RandomNumber.class);
//				numbers.add(randomNumber);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return numbers;
//
//	}
//
//}
