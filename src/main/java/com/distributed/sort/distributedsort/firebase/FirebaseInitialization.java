//package com.distributed.sort.distributedsort.firebase;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//import org.springframework.stereotype.Service;
//
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//
//import jakarta.annotation.PostConstruct;
//
//@Service
//public class FirebaseInitialization {
//
//	@PostConstruct
//	public void initialization() { 
//
//		try {
//			FileInputStream serviceAccount = new FileInputStream("./serviceAccountKey.json");
//
//			FirebaseOptions options = new FirebaseOptions.Builder()
//					.setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();
//
//			FirebaseApp.initializeApp(options);
//			
//		} catch (FileNotFoundException e) {
//			
//			e.printStackTrace();
//			
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//			
//		}
//
//	}
//
//}
