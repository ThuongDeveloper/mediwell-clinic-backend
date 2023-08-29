/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package groub2.backend.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.SignUrlOption;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import groub2.backend.entities.Doctor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class FirebaseImageService implements IImageService {

    @Autowired
    Properties properties;

    @EventListener
    public void init(ApplicationReadyEvent event) {

        // initialize Firebase
        try {

            FileInputStream serviceAccount
                    = new FileInputStream("./serviceAccount.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket(properties.getBucketName())
                    .build();

            FirebaseApp.initializeApp(options);

        } catch (Exception ex) {

            ex.printStackTrace();

        }
    }

    public String uploadImage(Doctor doctor,MultipartFile file) throws IOException  {
        

        FileInputStream serviceAccount
                = new FileInputStream("./serviceAccount.json");
         
 
        
        Storage storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build()
                .getService();
       

        String contentType = file.getContentType();
        // Define the destination path in Firebase Storage
        String storagePath = "doctors/" +"1"  + "/" + file.getOriginalFilename();
        //String storagePath =file.getOriginalFilename();

        // Convert MultipartFile to byte array
        byte[] fileBytes = file.getBytes();

        // Upload the file to Firebase Storage
       
        BlobId blobId = BlobId.of("project-hk4-27286.appspot.com", storagePath);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(contentType).build();
        Blob blob = storage.create(blobInfo, fileBytes);
        
     

    // Generate a signed URL that never expires
      SignUrlOption signUrlOption = SignUrlOption.withV4Signature();
    URL signedUrl = blob.signUrl(7, TimeUnit.DAYS, signUrlOption);
    

      String urlIMAGE = "https://storage.cloud.google.com/"+"project-hk4-27286.appspot.com/" +storagePath;

   

        return urlIMAGE;
    }

    public List<String> listFilesInBucket(String bucketName) throws IOException {
        FileInputStream serviceAccount
                = new FileInputStream("./serviceAccount.json");
        Storage storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build()
                .getService();

        Bucket bucket = storage.get(bucketName);

        List<String> fileNames = new ArrayList<>();
        if (bucket != null) {
            Iterable<Blob> blobs = (Iterable<Blob>) bucket.list().iterateAll();
            for (Blob blob : blobs) {
                fileNames.add(blob.getName());
            }
        }
   
        return fileNames;
    }

    @Override
    public String getImageUrl(String name) {
        return String.format(properties.getImageUrl(), name);
    }

    @Override
    public String save(MultipartFile file) throws IOException {

        Bucket bucket = StorageClient.getInstance().bucket();

        String name = generateFileName(file.getOriginalFilename());

        bucket.create(name, file.getBytes(), file.getContentType());

        return name;
    }

    @Override
    public String save(BufferedImage bufferedImage, String originalFileName) throws IOException {

        byte[] bytes = getByteArrays(bufferedImage, getExtension(originalFileName));

        Bucket bucket = StorageClient.getInstance().bucket();

        String name = generateFileName(originalFileName);

        bucket.create(name, bytes);

        return name;
    }

    @Override
    public void delete(String name) throws IOException {

        Bucket bucket = StorageClient.getInstance().bucket();

        if (StringUtils.isEmpty(name)) {
            throw new IOException("invalid file name");
        }

        Blob blob = bucket.get(name);

        if (blob == null) {
            throw new IOException("file not found");
        }

        blob.delete();
    }

}
