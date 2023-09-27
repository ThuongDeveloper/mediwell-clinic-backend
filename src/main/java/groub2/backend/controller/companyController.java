package groub2.backend.controller;

import groub2.backend.entities.Company;
import groub2.backend.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class companyController {

    @Autowired
    CompanyService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Company> read() {
        return service.getAll();
    }
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@RequestBody Company company) {
        // Check if the name already exists in the database
        if (service.isCompanyNameExists(company.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Company Name already exists in the database.");
        }

        boolean created = service.saveCompany(company);
        if (created) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Creation successful.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Creation unsuccessful.");
        }
    }


    @GetMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Company getDonviByID(@PathVariable int id){

        Company obj = service.findCompanybyID(id).get();
        return obj;
    }


    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean edit(@RequestBody Company company){

        service.editCompany(company);

        return true;
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDonvi(@PathVariable int id) {
        boolean deleteSuccessful = service.deleteCompanyId(id);
        if (deleteSuccessful) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Xóa không thành công");
        }
    }

}
