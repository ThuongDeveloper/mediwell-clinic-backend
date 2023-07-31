/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package groub2.backend.service;

import groub2.backend.entities.TypeDoctor;
import groub2.backend.res.TypeDoctorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class TypeDoctorService {

    @Autowired
    TypeDoctorRepository res;

    public List<TypeDoctor> getAll() {
        return res.findAll();
    }

    public boolean saveTypeDoctor(TypeDoctor typedoctor) {

        try {
            res.save(typedoctor);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    public Optional<TypeDoctor> findTypeDoctorbyID(int id){
      return res.findById(id);
     
    }
    
    public void editTypeDoctor(TypeDoctor typeDoctor){
        res.save(typeDoctor);
    }
    public boolean deleteTypeDoctorId(int id){
        try {
            res.deleteById(id);
            return true;
        } catch (Exception e) {
        return false;
        }
        
    }
}
