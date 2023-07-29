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
    CasherService casherService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Casher> listCasher() {
        return casherService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Casher> getCasher(@PathVariable Integer id) {
        Casher casher = casherService.getCasherById(id);
        if (casher != null) {
            return new ResponseEntity<>(casher, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Casher> addCasher(@RequestBody Casher casher) {
        casherService.addCasher(casher);
        return new ResponseEntity<>(casher, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Casher> updateCasher(@PathVariable Integer id, @RequestBody Casher updatedCasher) {
        Casher casher = casherService.getCasherById(id);
        if (casher != null) {
            updatedCasher.setId(id);
            Casher updated = casherService.updateCasher(updatedCasher);
            if (updated != null) {
                return new ResponseEntity<>(updated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteCasher(@PathVariable Integer id) {
        Casher casher = casherService.getCasherById(id);
        if (casher != null) {
            casherService.deleteCasher(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

