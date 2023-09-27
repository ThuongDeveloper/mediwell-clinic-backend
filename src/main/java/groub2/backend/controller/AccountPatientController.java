/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package groub2.backend.controller;

import groub2.backend.dto.CheckLoginMobileDAO;
import groub2.backend.entities.Appointment;
import groub2.backend.entities.Patient;
import groub2.backend.res.AdminRespository;
import groub2.backend.service.AccountPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("/api/accountpatient")
public class AccountPatientController {
    
    @Autowired
    private AdminRespository resAdmin;
       @Autowired
    private AccountPatientService serviceAccount;
      @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;    
      
    @PostMapping("/checklogin")
    @ResponseStatus(HttpStatus.OK)  
    public ResponseEntity<Object> checkLogin(@RequestBody CheckLoginMobileDAO model) {
        try {
               var list = serviceAccount.SearchByEmail(model.email);
               if(list == null){
                   
                   var objAdmin = resAdmin.SearchByEmail(model.email);
                   if(objAdmin == null){
                       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                   }else{
                       return new ResponseEntity<>(objAdmin,HttpStatus.OK);
                   }
                   
                   
               }
           var aaa = bCryptPasswordEncoder.matches(model.password,list.getPassword());
          
         if (aaa == true) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
     
    
      
    }
    @RequestMapping(value = "/checklogin", method = RequestMethod.GET)
    public String Get() {
        
      
       return "sssss";
    }
}
