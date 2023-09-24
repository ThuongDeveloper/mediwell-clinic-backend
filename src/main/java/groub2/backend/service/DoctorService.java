/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package groub2.backend.service;

import groub2.backend.dto.DoctorWithRating;
import groub2.backend.entities.Casher;
import groub2.backend.entities.Doctor;
import groub2.backend.entities.TypeDoctor;
import groub2.backend.res.DoctorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class DoctorService {
    @Autowired
    DoctorRepository res;
       @Autowired
    
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public List<Object> getDoctorwithRating(){
        return res.getDoctorsWithAverageRating();
    }
    public List<Object> getDoctorwithRatingSearchName(String name){
        return res.getDoctorsWithAverageRatingAndFilterByName(name);
    }
 
    public List<Doctor> getAll(){
        return res.findAll();
    }
    
    public List<Doctor> filterDoctors(TypeDoctor type) {
        return res.findByTypeDoctorId(type);
    }
    
    public boolean saveDoctor(Doctor doctor){
        doctor.setRole("DOCTOR");
        try {
                res.save(doctor);
                return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public Optional<Doctor> getOneDoctorById(int id){
      return  res.findById(id);
      
      
    }
     public Doctor editDoctorNotAnh(Doctor doctor){
           Doctor doctorNew = res.findById(doctor.getId()).orElse(null);
        if (doctor.getName() != null) {
                doctorNew.setName(doctor.getName());
            }
            if (doctor.getPassword() != null) {
                doctorNew.setPassword(bCryptPasswordEncoder.encode(doctor.getPassword()));
            }
            if (doctor.getEmail() != null) {
                doctorNew.setEmail(doctor.getEmail());
            }
            if (doctor.getAddress() != null) {
                doctorNew.setAddress(doctor.getAddress());
            }
           
            if (doctor.getImage()!= null && doctor.getImage() != "") {
                doctorNew.setImage(doctor.getImage());
            }
               if (doctor.getGender()!= null ) {
                doctorNew.setGender(doctor.getGender());
            }
                  if (doctor.getTypeDoctorId()!= null ) {
                doctorNew.setTypeDoctorId(doctor.getTypeDoctorId());
            }
                  
             return res.save(doctorNew);
     
     
    }
     
     
   public boolean deleteDoctorId(int id){
        try {
            res.deleteById(id);
            return true;
        } catch (Exception e) {
        return false;
        }
        
    }
    
    
}
