package groub2.backend.controller;


import groub2.backend.entities.Donthuoc;
import groub2.backend.entities.Thuoc;
import groub2.backend.service.DonthuocService;
import groub2.backend.service.ThuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/donthuoc")
public class DonthuocController {
    @Autowired
    DonthuocService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Donthuoc> read(){
        return  service.getAll();
    }
}
