/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package groub2.backend.controller;

import groub2.backend.dto.DoctorWithRating;
import groub2.backend.entities.Casher;
import groub2.backend.entities.Doctor;
import groub2.backend.entities.TypeDoctor;
import groub2.backend.firebase.FirebaseImageService;
import groub2.backend.service.DoctorService;
import java.util.Date;
import java.io.IOException;
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

    public boolean create(@RequestPart Doctor Doctor, @RequestPart("file") MultipartFile file) {
            
        try {
           Doctor.setPassword(bCryptPasswordEncoder.encode(Doctor.getPassword()));
        Doctor.setRole("DOCTOR");
        Doctor.setCreateAt(new Date());
           String urlIMG =  _FirebaseImageService.uploadImage(Doctor, file);
            Doctor.setImage(urlIMG);
             var flag = service.saveDoctor(Doctor);
            return flag;
        } catch (IOException ex) {
            Logger.getLogger(DoctorController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    @GetMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Doctor getOneDoctor(@PathVariable int id) {
        Doctor obj = service.getOneDoctorById(id).get();
        return obj;
    }

    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Doctor> getOneDoctor(@RequestBody Doctor Doctor) {
        var model = service.editTypeDoctor(Doctor);

        return new ResponseEntity<>(HttpStatus.OK);
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
   
}
