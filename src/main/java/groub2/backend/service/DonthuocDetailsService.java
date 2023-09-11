/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package groub2.backend.service;

import groub2.backend.entities.Donthuoc;
import groub2.backend.entities.DonthuocDetails;
import groub2.backend.res.DonThuocDetailsRepository;
import groub2.backend.res.DonthuocRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class DonthuocDetailsService {
     @Autowired
    DonThuocDetailsRepository res;
     
    
 //    public List<DonthuocDetails> getListById(int id){
//         return res.getListbyID(id);
 //    }
    public List<DonthuocDetails> getAll() {
        return res.findAll() ; 
    }
    
    

    public boolean saveDonthuoc(DonthuocDetails donthuocDetail) {

        try {
            res.save(donthuocDetail);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
