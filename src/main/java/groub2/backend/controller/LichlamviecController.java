    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package groub2.backend.controller;

import groub2.backend.entities.Casher;
import groub2.backend.entities.Doctor;
import groub2.backend.entities.Lichlamviec;
import groub2.backend.service.LichlamviecService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author hokim
 */
@RestController
@RequestMapping("/api/lichlamviec")
public class LichlamviecController {
    @Autowired
    LichlamviecService lichService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Lichlamviec> listLich() {
        return lichService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lichlamviec> getLich(@PathVariable Integer id) {
        Lichlamviec lich = lichService.getLichById(id);
        if (lich != null) {
            return new ResponseEntity<>(lich, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Lichlamviec> addLich(@RequestBody Lichlamviec lich) {
          
        lichService.addLich(lich);
        return new ResponseEntity<>(lich, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Lichlamviec> updateLich(@PathVariable Integer id, @RequestBody Lichlamviec updatedLich) {
        Lichlamviec lich = lichService.getLichById(id);
        if (lich != null) {
            updatedLich.setId(id);
            Lichlamviec updated = lichService.updateLich(updatedLich);
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
    public ResponseEntity<?> deleteLich(@PathVariable Integer id) {
        Lichlamviec lich = lichService.getLichById(id);
        if (lich != null) {
            lichService.deleteLich(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/getfromdoctor/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Lichlamviec>> getLichFromDoctor(@PathVariable Integer id) {
          Doctor newDoctor = new Doctor();
          newDoctor.setId(id);
       var lich = lichService.getLichByDoctorID(newDoctor);
        return new ResponseEntity<>(lich, HttpStatus.OK);
    }
}
