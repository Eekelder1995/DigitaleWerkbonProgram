package Firestore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import main.Client;
import main.Equipment;

public class Read {
	
	private Firestore db;
	private List<Equipment> equipmentList = new ArrayList<Equipment>();
	private List<Client> clientList = new ArrayList<Client>();
	
	private static final String equipmentCollection = "EquipmentNew";
	private static final String taskCollection = "Tasks";
	private static final String clientCollection = "Clients";
	private static final String employeeCollection = "Employees";

	
	public void initialize(){
		
		 
		try {
			FileInputStream serviceAccount = new FileInputStream("C:/Users/jur-e/Documents/Eclipse/key.json");

			FirebaseOptions options = null;
			try {
				GoogleCredentials credentials =  GoogleCredentials.fromStream(serviceAccount);
				options = new FirebaseOptions.Builder()
						.setCredentials(credentials)
						.build();
			} catch (IOException e) {
				e.printStackTrace();
			}

			FirebaseApp.initializeApp(options);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		db = FirestoreClient.getFirestore();
		
		try {
			downloadEquipmentList();
			//downloadEmployeeList();
			//downloadTaskList();
			downloadClientList();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
	}
	
	private void downloadClientList() throws InterruptedException, ExecutionException {

		ApiFuture<QuerySnapshot> query = db.collection(clientCollection).get();
		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot document : documents) {
			
			if (!document.getId().contains("version")){
			Client client = document.toObject(Client.class);
			clientList.add(client);
			}

		}
		
		for (Client c : clientList){
			System.out.println(c.getFirstName());
		}
		
	}

	private void downloadEquipmentList() throws InterruptedException, ExecutionException {
				
		ApiFuture<QuerySnapshot> query = db.collection(equipmentCollection).get();
		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot document : documents) {
			
			if (!document.getId().contains("version")){
			Equipment equipment = document.toObject(Equipment.class);
			equipmentList.add(equipment);
			}

		}
		
		for (Equipment e : equipmentList){
			System.out.println(e.getType());
		}
				
	}
	


}
