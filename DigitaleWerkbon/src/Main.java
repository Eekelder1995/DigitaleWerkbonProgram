import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteBatch;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.FirebaseOptions.Builder;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		int j = 1;
		System.out.println(j+j);
		// TODO Auto-generated method stub

		FileInputStream serviceAccount;
		try {
			serviceAccount = new FileInputStream("C:/Users/jur-e/Documents/Eclipse/key.json");

			FirebaseOptions options = null;
			try {
				GoogleCredentials credentials =  GoogleCredentials.fromStream(serviceAccount);
				options = new FirebaseOptions.Builder()
						.setCredentials(credentials)
						.build();
						//.setDatabaseUrl("https://planning-97f0b.firebaseio.com").build();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			FirebaseApp.initializeApp(options);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		FirestoreOptions options = 
//				  FirestoreOptions.newBuilder().setTimestampsInSnapshotsEnabled(true).build();
//		Firestore firestore = options.getService();

		Firestore db = FirestoreClient.getFirestore();

		ApiFuture<QuerySnapshot> query = db.collection("Employees").get();
		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot document : documents) {
			System.out.println("Documentname: " + document.getId());
			System.out.println("First: " + document.getString("firstName"));

		}
		
		
	}
	
	

}
