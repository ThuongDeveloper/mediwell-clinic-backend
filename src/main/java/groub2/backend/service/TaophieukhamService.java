/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package groub2.backend.service;

import groub2.backend.entities.Taophieukham;
import groub2.backend.res.TaophieukhamRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ann
 */
@Service
public class TaophieukhamService {
    @Autowired
    TaophieukhamRepository res;
    
    public List<Taophieukham> findAll(){
        return res.findAll();
    }
    
    public Taophieukham findByName(String name){
        return res.findByName(name);
    }
    
    public void addTaophieukham(Taophieukham newTaophieukham){
        res.save(newTaophieukham);
    }
    
    public void deleteTaophieukham(Integer id){
        res.deleteById(id);
    }
}
