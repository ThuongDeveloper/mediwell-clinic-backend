
package groub2.backend.controller;
import groub2.backend.entities.Typethuoc;
import groub2.backend.res.TypethuocRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/typethuoc")
public class TypethuocController {

     @Autowired
    private TypethuocRepository typethuocRepository;

     @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Typethuoc> getAllTypethuoc() {
        return typethuocRepository.findAll();
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Typethuoc createTypethuoc(@RequestBody Typethuoc typethuoc) {
        return typethuocRepository.save(typethuoc);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Typethuoc updateTypethuoc(@PathVariable Integer id, @RequestBody Typethuoc typethuoc) {
        typethuoc.setId(id);
        return typethuocRepository.save(typethuoc);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTypethuoc(@PathVariable Integer id) {
        typethuocRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Typethuoc getTypethuoc(@PathVariable Integer id) {
        return typethuocRepository.findById(id).orElse(null);
    }

    

    // ...
}
