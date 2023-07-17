/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package groub2.backend.controller;

import groub2.backend.entities.Taophieukham;
import groub2.backend.service.TaophieukhamService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Ann
 */
@RestController
@RequestMapping("/api/taophieukham")
public class TaophieukhamController {
    
    @Autowired
     TaophieukhamService Tservice;
    
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Taophieukham> listTaophieukham() {
        return Tservice.findAll();
    }
    
    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {
        return null;
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTaophieukham(@RequestBody Taophieukham taophieukham) {
        Tservice.addTaophieukham(taophieukham);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
        return null;
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaophieukham(@PathVariable Integer id) {
        Tservice.deleteTaophieukham(id);
    }
    
}
