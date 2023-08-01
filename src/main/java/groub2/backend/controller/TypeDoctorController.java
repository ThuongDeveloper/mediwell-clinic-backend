/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package groub2.backend.controller;

import groub2.backend.entities.TypeDoctor;
import groub2.backend.service.TypeDoctorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("/api/typedoctor")
public class TypeDoctorController {
    
    @Autowired
    TypeDoctorService service;
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TypeDoctor> read() {
        return service.getAll();
    }
    @PostMapping("/create")
        @ResponseStatus(HttpStatus.CREATED)
    public boolean read(@RequestBody TypeDoctor typeDoctor) {
       
        return service.saveTypeDoctor(typeDoctor);
    }
    
    @GetMapping("/edit/{id}")
          @ResponseStatus(HttpStatus.OK)
    public TypeDoctor getTypeDoctorByID(@PathVariable int id){
        
        TypeDoctor obj = service.findTypeDoctorbyID(id).get();
        return obj;
    }
    
       
    @PutMapping("/edit")
          @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean edit(@RequestBody TypeDoctor typeDoctor){
        
        service.editTypeDoctor(typeDoctor);
  
        return true;
    }
    
    
       @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        boolean deleteSuccessful = service.deleteTypeDoctorId(id);
        if (deleteSuccessful) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Xóa không thành công");
        }
    }
}
