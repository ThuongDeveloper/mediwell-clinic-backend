/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package groub2.backend.controller;

import groub2.backend.entities.Patient;
import groub2.backend.firebase.FirebaseImageService;
import groub2.backend.service.PatientService;
import java.io.IOException;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author admin
 */
@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    FirebaseImageService _FirebaseImageService;

    @Autowired
    PatientService patientService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> listCasher() {
        return patientService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable Integer id) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Patient> addPatient(@RequestPart Patient patient, @RequestPart("file") MultipartFile file) throws IOException {
        patient.setPassword(bCryptPasswordEncoder.encode(patient.getPassword()));
        patient.setRole("PATIENT");
        String urlIMG = _FirebaseImageService.uploadImagePatient(patient, file);
        patient.setImage(urlIMG);
        patientService.addPatient(patient);
        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    @PutMapping(value = "/edit/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Patient> updatePatient(
            @PathVariable Integer id,
            @RequestPart(name = "file", required = false) MultipartFile file,
            @RequestPart("updatedPatient") Patient updatedPatient
    ) throws IOException {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            // Xử lý tệp tin ảnh nếu tệp không trống
            if (file != null && !file.isEmpty()) {
                String urlIMG = _FirebaseImageService.uploadImagePatient(updatedPatient, file);
                updatedPatient.setImage(urlIMG);
            }

            // Kiểm tra và cập nhật các trường dữ liệu khác của updatedPatient
            updatedPatient.setId(id);
            Patient updated = patientService.updatePatient(updatedPatient);

            if (updated != null) {
                return new ResponseEntity<>(updated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deletePatient(@PathVariable Integer id) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            patientService.deletePatient(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/checkEmail/{email}")
    public ResponseEntity<Boolean> checkEmail(@PathVariable String email) {
        // Kiểm tra email tồn tại trong bất kỳ loại người dùng nào
        boolean exists = patientService.checkEmailExistsForAnyUserType(email);
        return ResponseEntity.ok(exists);
    }

}
