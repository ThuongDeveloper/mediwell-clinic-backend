///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package groub2.backend.firebase;
//
//import com.google.api.core.ApiFuture;
//import com.google.cloud.firestore.DocumentSnapshot;
//import com.google.cloud.firestore.Firestore;
//import com.google.cloud.firestore.QueryDocumentSnapshot;
//import com.google.cloud.firestore.QuerySnapshot;
//import com.google.cloud.firestore.WriteResult;
//import com.google.firebase.cloud.FirestoreClient;
//import java.util.concurrent.ExecutionException;
//import org.springframework.stereotype.Service;
//
///**
// *
// * @author DELL
// */
//@Service
//public class FirebaseService {   
//    public String saveUserDetails(String aaa,String bbb) throws InterruptedException, ExecutionException{
//        Firestore dbFirestore = FirestoreClient.getFirestore();
//        
//        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection("users").document(aaa).set(bbb);
//        return collectionApiFuture.get().getUpdateTime().toString();
//        
//    }
//    
//    public String GetDuLieu() throws InterruptedException, ExecutionException{
//          Firestore dbFirestore = FirestoreClient.getFirestore();
//        
//        // Lấy dữ liệu của một tài liệu cụ thể
//        String documentId = "AqEZucsDneaBqsITDize";
//        ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = dbFirestore.collection("users").document(documentId).get();
//        DocumentSnapshot documentSnapshot = documentSnapshotApiFuture.get();
//        if (documentSnapshot.exists()) {
//            // Lấy dữ liệu từ tài liệu
//            String data = documentSnapshot.getString("");
//            System.out.println("Data: " + data);
//        } else {
//            System.out.println("Document does not exist");
//        }
//        
//        // Lấy dữ liệu của tất cả tài liệu trong một collection
//        ApiFuture<QuerySnapshot> querySnapshotApiFuture = dbFirestore.collection("users").get();
//        QuerySnapshot querySnapshot = querySnapshotApiFuture.get();
//        for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
//            // Lấy dữ liệu từ mỗi tài liệu
//            String data = document.getString("your_field_name");
//            System.out.println("Data: " + data);
//               
//        }
//
//
//    
// return "Ssss";
//
//    }
//    
//}
