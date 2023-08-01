/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package groub2.backend.service;

import groub2.backend.entities.Typethuoc;
import groub2.backend.res.TypethuocRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dochi
 */
@Service
public class TypethuocService {
     @Autowired
    TypethuocRepository res;

    public List<Typethuoc> getAll() {
        return res.findAll();
    }

    public boolean saveTypeThuoc(Typethuoc typethuoc) {

        try {
            res.save(typethuoc);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    public Optional<Typethuoc> findTypethuocbyID(int id){
      return res.findById(id);
     
    }
    
    public void editTypethuoc(Typethuoc typethuoc){
        res.save(typethuoc);
    }
    public boolean deleteTypethuocId(int id){
        try {
            res.deleteById(id);
            return true;
        } catch (Exception e) {
        return false;
        }
        
    }
}
