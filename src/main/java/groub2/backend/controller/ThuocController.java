/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package groub2.backend.controller;

import groub2.backend.entities.Thuoc;
import groub2.backend.entities.Typethuoc;
import groub2.backend.res.ThuocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/thuoc")
public class ThuocController {

    @Autowired
    private ThuocRepository thuocRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Thuoc> getAllthuoc() {
        return thuocRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Thuoc> createThuoc(@RequestBody Thuoc thuoc) {
        Thuoc createdThuoc = thuocRepository.save(thuoc);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdThuoc);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Thuoc> updateThuoc(@PathVariable Integer id, @RequestBody Thuoc thuoc) {
        thuoc.setId(id);
        Thuoc updatedThuoc = thuocRepository.save(thuoc);
        return ResponseEntity.ok(updatedThuoc);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteThuoc(@PathVariable Integer id) {
        thuocRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

     @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Thuoc getTypethuoc(@PathVariable Integer id) {
        return thuocRepository.findById(id).orElse(null);
    }

    
}
