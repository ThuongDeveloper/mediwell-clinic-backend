/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package groub2.backend.service;

import groub2.backend.entities.Patient;
import groub2.backend.res.AccountPatientRepository;
import groub2.backend.res.CasherRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class AccountPatientService {
    @Autowired
    AccountPatientRepository res;
    
   
    public Patient SearchByEmail(String name) {
       return res.SearchByEmail(name);
    }
}
