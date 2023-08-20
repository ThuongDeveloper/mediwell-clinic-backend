package groub2.backend.controller;

import groub2.backend.entities.Taophieukham;
import groub2.backend.service.TaophieukhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public ResponseEntity<Taophieukham> get(@PathVariable Integer id) {
        Taophieukham taophieukham = Tservice.getTaophieukhamById(id);
        if (taophieukham != null) {
            return new ResponseEntity<>(taophieukham, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Taophieukham> addTaophieukham(@RequestBody Taophieukham taophieukham) {
        Tservice.addTaophieukham(taophieukham);
        return new ResponseEntity<>(taophieukham, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Taophieukham> updateTaophieukham(@PathVariable Integer id, @RequestBody Taophieukham updatedTaophieukham) {
        Taophieukham taophieukham = Tservice.getTaophieukhamById(id);
        if (taophieukham != null) {
            updatedTaophieukham.setId(id);
            Taophieukham updated = Tservice.updateTaophieukham(updatedTaophieukham);
            if (updated != null) {
                return new ResponseEntity<>(updated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteTaophieukham(@PathVariable int id) {
        Taophieukham taophieukham = Tservice.getTaophieukhamById(id);
        if (taophieukham != null) {
            Tservice.deleteTaophieukham(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Xóa không thành công");
        }
    }
}
