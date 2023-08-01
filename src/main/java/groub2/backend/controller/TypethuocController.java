
package groub2.backend.controller;
import groub2.backend.entities.Typethuoc;
import groub2.backend.res.TypethuocRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public Typethuoc updateTypethuoc(@PathVariable Integer id, @RequestBody Typethuoc updatedTypethuoc) {
        // Tìm đối tượng Typethuoc theo ID trong cơ sở dữ liệu
        Optional<Typethuoc> existingTypethuocOptional = typethuocRepository.findById(id);

        if (existingTypethuocOptional.isPresent()) {
            Typethuoc existingTypethuoc = existingTypethuocOptional.get();

            // Cập nhật thông tin của đối tượng Typethuoc từ yêu cầu PUT
            existingTypethuoc.setName(updatedTypethuoc.getName());
            // Cập nhật các thuộc tính khác của Typethuoc (nếu có)

            // Lưu đối tượng đã cập nhật vào cơ sở dữ liệu
            return typethuocRepository.save(existingTypethuoc);
        } else {
            // Không tìm thấy đối tượng với ID tương ứng, ném ra ngoại lệ ResponseStatusException với mã lỗi 404 (Not Found)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Typethuoc not found with ID: " + id);
        }
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
