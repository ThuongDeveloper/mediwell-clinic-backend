/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package groub2.backend.controller;

import groub2.backend.entities.Doctor;
import groub2.backend.entities.TypeDoctor;
import groub2.backend.service.DoctorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Ann
 */
@RestController
@RequestMapping("/api/filter")
public class FilterController {
    
    @Autowired
    DoctorService doctorService;
    
    @GetMapping("/doctor/{type}")
    public ResponseEntity<List<Doctor>> filterDoctorsByType(@PathVariable String type) {
        TypeDoctor typeDoctor = new TypeDoctor();
        typeDoctor.setId(Integer.parseInt(type));
        List<Doctor> filteredDoctors = doctorService.filterDoctors(typeDoctor);
        return ResponseEntity.ok(filteredDoctors);
    }
}
