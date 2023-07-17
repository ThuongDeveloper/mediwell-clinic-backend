/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package groub2.backend.service;

import groub2.backend.entities.Casher;
import groub2.backend.res.CasherRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ann
 */
@Service
public class CasherService {
    @Autowired
    CasherRepository res;
    
    public List<Casher> findAll(){
        return res.findAll();
    }
    
    public Casher findByName(String name){
        return res.findByName(name);
    }
    
    public void addCasher(Casher newCasher){
        res.save(newCasher);
    }
    
    public void deleteCasher(Integer id){
        res.deleteById(id);
    }
}
