///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package groub2.backend.firebase;
//
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.firebase.FirebaseApp;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import com.google.firebase.FirebaseOptions;
//import java.io.Console;
//import java.io.File;
//import java.io.IOException;
//import javax.annotation.PostConstruct;
//import org.springframework.stereotype.Service;
//
///**
// *
// * @author DELL
// */
//@Service
//public class FirebaseInitialize {
//
//    @PostConstruct
//    public void initialize() throws FileNotFoundException, IOException {
//        String relativeFilePath = "serviceAccount.json";
//    
//        // Tạo đối tượng File từ đường dẫn tương đối
//        File file = new File(relativeFilePath);
//        
//        // Lấy đường dẫn tuyệt đối của tệp
//        String absolutePath = file.getAbsolutePath();
//            System.out.println("-XXXXXXXXXXXXXXXXXXx-XXXXXXXXXXXXXXXXXXXX_xxxxxxxxxxxxxxxxxxxxxxxx");
//        System.out.println(absolutePath);
//        FileInputStream serviceAccount
//                = new FileInputStream("./serviceAccount.json");
//
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .build();
//
//        FirebaseApp.initializeApp(options);
//    }
//}
