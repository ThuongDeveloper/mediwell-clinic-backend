package groub2.backend.controller;


import groub2.backend.dto.listToathuocDAO;
import groub2.backend.entities.*;
import java.util.Date;

import groub2.backend.service.ToathuocDetailsService;
import groub2.backend.service.ToathuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/toathuoc")
public class ToathuocController {
    @Autowired
    ToathuocService toathuocService;
    @Autowired
    ToathuocDetailsService toathuocDetailsService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Toathuoc> read(){
        return  toathuocService.getAll();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity addDoctor(@RequestBody listToathuocDAO listToathuocDAO) {

        var listobj = listToathuocDAO.getListTT();

        Toathuoc newToathuoc = new Toathuoc();
        newToathuoc.setCreateAt(new Date());
        newToathuoc.setName(listToathuocDAO.getName());
        newToathuoc.setPhone(listToathuocDAO.getPhone());
        newToathuoc.setAddress(listToathuocDAO.getAddress());
        newToathuoc.setSymptom(listToathuocDAO.getSymptom());
        newToathuoc.setDescription(listToathuocDAO.getDescription());
        newToathuoc.setState(listToathuocDAO.isState());

        var modelToathuoc =  toathuocService.saveToathuoc(newToathuoc);


        for(int i = 0 ; i < listobj.size();i++){
            Thuoc newThuoc = new Thuoc();
            newThuoc.setId(listobj.get(i).getThuocID());
            ToathuocDetails newToathuocDetails = new ToathuocDetails();
            newToathuocDetails.setToathuocId(modelToathuoc);
            newToathuocDetails.setChieu(listobj.get(i).getChieu());
            newToathuocDetails.setToi(listobj.get(i).getToi());
            newToathuocDetails.setSang(listobj.get(i).getSang());
            newToathuocDetails.setTrua(listobj.get(i).getTrua());
            newToathuocDetails.setQuantity(listobj.get(i).getQuantity());
            newToathuocDetails.setThuocId(newThuoc);
            toathuocDetailsService.saveToathuoc(newToathuocDetails);
        }

        var a = listToathuocDAO;
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
