/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package groub2.backend.service;

import groub2.backend.dto.DoctorWithRating;
import groub2.backend.entities.Doctor;
import groub2.backend.entities.TypeDoctor;
import groub2.backend.res.DoctorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class DoctorService {
    @Autowired
    DoctorRepository res;
    
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
     public Doctor editTypeDoctor(Doctor doctor){
        res.save(doctor);
        return doctor;
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
