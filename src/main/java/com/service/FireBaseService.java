package com.service;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.entity.Person;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FireBaseService {

public String savePerson(Person person) throws InterruptedException, ExecutionException {
	Firestore dbstore = FirestoreClient.getFirestore();
	ApiFuture<WriteResult> collectionApi = dbstore.collection("user").document(person.getName()).set(person);
	return collectionApi.get().getUpdateTime().toString();
}

public Person getPerson(String name) throws InterruptedException, ExecutionException {
	Firestore dbstore = FirestoreClient.getFirestore();
	DocumentReference docref = dbstore.collection("user").document(name);
	ApiFuture<DocumentSnapshot> future = docref.get();
	DocumentSnapshot docsnap = future.get();
	
	Person person = null;
	if(docsnap.exists()) {
		person = docsnap.toObject(Person.class);
		return person;
	}else {
		return null;
	}
}

public String updatePerson(Person person) throws InterruptedException, ExecutionException {
			Firestore dbstore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionApi = dbstore.collection("user").document(person.getName()).set(person);
		return collectionApi.get().getUpdateTime().toString();
	}	

public String deletePerson(String name) {
	Firestore dbstore = FirestoreClient.getFirestore();
	ApiFuture<WriteResult> collectionApi = dbstore.collection("user").document(name).delete();
	return "person has been deleted successfully";

}
}

