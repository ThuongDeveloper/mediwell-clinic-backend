/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package groub2.backend.controller;

import com.google.j2objc.annotations.AutoreleasePool;
import groub2.backend.dto.DoctorWithRating;
import groub2.backend.entities.Casher;
import groub2.backend.entities.Doctor;
import groub2.backend.entities.TypeDoctor;
import groub2.backend.firebase.FirebaseImageService;
import groub2.backend.res.AdminRespository;
import groub2.backend.service.CasherService;
import groub2.backend.service.DoctorService;
import groub2.backend.service.PatientService;
import java.util.Date;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    FirebaseImageService _FirebaseImageService;
    @Autowired
    DoctorService service;
    @Autowired
    PatientService patientServices;
    @Autowired
     CasherService casherService;
     @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Doctor> read() {
        return service.getAll();
    }
   
//    public boolean create(@RequestBody Doctor Doctor) {
//        
//        var flag = service.saveDoctor(Doctor);
//
//        return flag;
//    }
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)

   
    public ResponseEntity<String>  create(@RequestPart Doctor Doctor, @RequestPart("file") MultipartFile file) {
        
        boolean flag = true;
        var listCasher =    casherService.findAll();
                 var listPatient =    patientServices.findAll();
                 var listAdmin = patientServices.findAll();
                 var listDoctor = service.getAll();
                   for(var item : listCasher){
                     if(item.getEmail().equals(Doctor.getEmail())){
                     flag = false;
                         break;
                             };
                 }
                   for(var item : listPatient){
                     if(item.getEmail().equals(Doctor.getEmail())){
                     flag = false;
                      break;
                             };
                 }
                    for(var item : listAdmin){
                     if(item.getEmail().equals(Doctor.getEmail())){
                     flag = false;
                      break;
                             };
                 }
                    for(var item : listDoctor){
                     if(item.getEmail().equals(Doctor.getEmail())){
                     flag = false;
                      break;
                             };
                 }
                    if( flag == false){
                                  return new ResponseEntity<>("EmailTonTai",HttpStatus.OK);

                    }
        
        try {
           Doctor.setPassword(bCryptPasswordEncoder.encode(Doctor.getPassword()));
        Doctor.setRole("DOCTOR");
        Doctor.setCreateAt(new Date());
           String urlIMG =  _FirebaseImageService.uploadImageDoctor(Doctor, file);
            Doctor.setImage(urlIMG);
             var flag1 = service.saveDoctor(Doctor);
             if(flag1 == true){
                      return new ResponseEntity<>( "Success",HttpStatus.OK);
             }else{
                           return new ResponseEntity<>( "Failed",HttpStatus.INTERNAL_SERVER_ERROR);

             }
       
        } catch (IOException ex) {
            Logger.getLogger(DoctorController.class.getName()).log(Level.SEVERE, null, ex);
          return new ResponseEntity<>( "Failed",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    
     @PostMapping(value = "/editnotanh")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Doctor> updateNotAnh(@RequestBody Doctor Doctor) {
    
        var model = service.editDoctorNotAnh(Doctor);

        return new ResponseEntity<>(model,HttpStatus.OK);
    }
    @PostMapping(value = "/edit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateCoanh(@RequestPart Doctor Doctor,@RequestPart("file") MultipartFile file) {
     

        // Xử lý tệp tin ảnh nếu tệp không trống
        if (file != null && !file.isEmpty()) {
            try {
                String urlIMG = _FirebaseImageService.uploadImageDoctor(Doctor, file);
                Doctor.setImage(urlIMG);
            
            } catch (IOException ex) {
               return new ResponseEntity<>("error",HttpStatus.OK);
            }
        }

        // Kiểm tra và cập nhật các trường dữ liệu khác của updatedPatient
       Doctor doctor =    service.editDoctorNotAnh(Doctor);
  

        if (doctor != null) {
            return new ResponseEntity<>("success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("error",HttpStatus.OK);
        }
        
        
     
    }

    
    @GetMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Doctor getOneDoctor(@PathVariable int id) {
        Doctor obj = service.getOneDoctorById(id).get();
        return obj;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        boolean deleteSuccessful = service.deleteDoctorId(id);
        if (deleteSuccessful) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Xóa không thành công");
        }
    }
     @GetMapping("/withrating")
    @ResponseStatus(HttpStatus.OK)
    public List<Object> getDoctorwithRating() {
        return service.getDoctorwithRating();
    
    }
         @GetMapping("/withrating/search={search}")
    @ResponseStatus(HttpStatus.OK)
    public List<Object> getDoctorwithRatingAndFilter(@PathVariable String search) {
        var list = service.getDoctorwithRating();

        if(search != null && search != ""){
            list = service.getDoctorwithRatingSearchName(search);
        }else{
            list = service.getDoctorwithRating();
        }
        return list;
    
    }
   
}
