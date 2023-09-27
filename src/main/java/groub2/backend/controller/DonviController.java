package groub2.backend.controller;

import groub2.backend.entities.Donvi;
import groub2.backend.entities.Typethuoc;
import groub2.backend.service.DonviService;
import groub2.backend.service.TypethuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donvi")
public class DonviController {

    @Autowired
    DonviService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Donvi> read() {
        return service.getAll();
    }
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@RequestBody Donvi donvi) {
        // Check if the name already exists in the database
        if (service.isDonviNameExists(donvi.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name already exists in the database.");
        }

        boolean created = service.saveDonvi(donvi);
        if (created) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Creation successful.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Creation unsuccessful.");
        }
    }


    @GetMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Donvi getDonviByID(@PathVariable int id){

        Donvi obj = service.findDonvibyID(id).get();
        return obj;
    }


    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean edit(@RequestBody Donvi donvi){

        service.editDonvi(donvi);

        return true;
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDonvi(@PathVariable int id) {
        boolean deleteSuccessful = service.deleteDonviId(id);
        if (deleteSuccessful) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Xóa không thành công");
        }
    }


    // ...
}
