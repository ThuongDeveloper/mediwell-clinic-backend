/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package groub2.backend.controller;

import groub2.backend.entities.Casher;
import groub2.backend.entities.Doctor;
import groub2.backend.entities.TypeDoctor;
import groub2.backend.service.DoctorService;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author DELL
 */
@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    DoctorService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Doctor> read() {
        return service.getAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public boolean create(@RequestBody Doctor Doctor) {

        var flag = service.saveDoctor(Doctor);

        return flag;

    }

    @GetMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Doctor getOneDoctor(@PathVariable int id) {
        Doctor obj = service.getOneDoctorById(id).get();
        return obj;
    }

    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Doctor> getOneDoctor(@RequestBody Doctor Doctor) {
        var model = service.editTypeDoctor(Doctor);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        boolean deleteSuccessful = service.deleteDoctorId(id);
        if (deleteSuccessful) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Xóa không thành công");
        }
    }
}
