/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package groub2.backend.controller;

import groub2.backend.entities.Casher;
import groub2.backend.service.CasherService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Ann
 */
@RestController
@RequestMapping("/api/casher")
public class CasherController {
    
    @Autowired
    CasherService Cservice;
    
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Casher> listCasher() {
        return Cservice.findAll();
    }
    
    @GetMapping("/{id}")
    public Object getCasher(@PathVariable String id) {
        return null;
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCasher(@RequestBody Casher casher) {
        Cservice.addCasher(casher);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
        return null;
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCasher(@PathVariable Integer id) {
        Cservice.deleteCasher(id);
    }
    
}
