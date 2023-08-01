/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package groub2.backend.controller;

import groub2.backend.entities.Thuoc;
import groub2.backend.service.ThuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/thuoc")
public class ThuocController {

    @Autowired
    ThuocService service;
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Thuoc> read(){
      return  service.getAll();
    }
    
        @PostMapping("/create")
        @ResponseStatus(HttpStatus.OK)
          public boolean create(@RequestBody Thuoc thuoc){

            var flag =  service.saveThuoc(thuoc);

            return flag;

        }

      @GetMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Thuoc getOneThuoc(@PathVariable int id){
       Thuoc obj = service.getOneThuocById(id).get();
      return  obj;
    }
    
    @PutMapping("/edit")
      @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Thuoc> getOneThuoc(@RequestBody Thuoc thuoc){
           var model =  service.editTypeThuoc(thuoc);
            
     return new ResponseEntity<>(HttpStatus.OK);
    }
    
      @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        boolean deleteSuccessful = service.deleteThuocId(id);
        if (deleteSuccessful) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Xóa không thành công");
        }
    }

}
