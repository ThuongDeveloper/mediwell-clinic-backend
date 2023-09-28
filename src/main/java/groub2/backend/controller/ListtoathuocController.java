/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package groub2.backend.controller;

import groub2.backend.entities.Taophieukham;
import groub2.backend.entities.Toathuoc;
import groub2.backend.entities.TypeDoctor;
import groub2.backend.service.ListtoathuocService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Ann
 */
@RestController
@RequestMapping("/api/listtoathuoc")
public class ListtoathuocController {
    
    @Autowired
    ListtoathuocService service;

    @GetMapping("")
    public ResponseEntity<List<Toathuoc>> getTypeDoctorAndDate() {
        List<Toathuoc> toathuoc = service.getAllByCreateAtToday();
        if (toathuoc != null) {
            return new ResponseEntity<>(toathuoc, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
