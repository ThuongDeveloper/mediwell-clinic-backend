package groub2.backend.controller;

import groub2.backend.entities.Taophieukham;
import groub2.backend.entities.TypeDoctor;
import groub2.backend.service.ListphieukhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/listphieukham")
public class ListPhieuKhamController {
    @Autowired
    ListphieukhamService service;

    @GetMapping("/{typedoctor}")
    public ResponseEntity<List<Taophieukham>> getTypeDoctorAndDate(@PathVariable TypeDoctor typedoctor) {
        // Lấy ngày tạo trong hôm nay
        Date today = new Date();

        List<Taophieukham> taophieukham = service.getTaophieukhamByTypeDoctorAndCreateAt(typedoctor, today);
        if (taophieukham != null) {
            return new ResponseEntity<>(taophieukham, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
