/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package groub2.backend.controller;

import groub2.backend.entities.Appointment;
import groub2.backend.entities.Doctor;
import groub2.backend.entities.Patient;
import groub2.backend.service.AppointmentService;
import java.util.List;
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
 *ArgumentError (Invalid argument(s): No host specified in URI api/appointment/getAllByPatient/16)
 * @author hokim
 */
@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> listAppointment() {
        return appointmentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable Integer id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        if (appointment != null) {
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment) {
         appointment.setTypePayment("ONLINE");
         appointment.setPrice(50);
         appointment.setStatus(Boolean.FALSE);
        appointmentService.addAppointment(appointment);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Appointment updateAppointment(@PathVariable int id, @RequestBody Appointment updatedAppointment) {
//        Appointment appointment = appointmentService.getAppointmentById(id);
//        if (appointment != null) {
//            updatedAppointment.setId(id);
//            Appointment updated = appointmentService.updateAppointment(updatedAppointment);
//            if (updated != null) {
//                return new ResponseEntity<>(updated, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return appointmentService.updateAppointment(updatedAppointment, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteAppointment(@PathVariable Integer id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        if (appointment != null) {
            appointmentService.deleteAppointment(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/getAllByPatient/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> getAllByPatient(@PathVariable Integer id) {
        Patient newPatient = new Patient();
        newPatient.setId(id);
        List<Appointment> appointment = appointmentService.getAllByPatientId(newPatient);
    return appointment;
    }
}

