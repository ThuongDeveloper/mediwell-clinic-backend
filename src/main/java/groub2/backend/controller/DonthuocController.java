package groub2.backend.controller;

import groub2.backend.dto.ListHoaDonThuocDAO;
import groub2.backend.entities.Casher;
import groub2.backend.entities.Donthuoc;
import groub2.backend.entities.DonthuocDetails;
import groub2.backend.entities.Thuoc;
import groub2.backend.service.DonthuocDetailsService;
import groub2.backend.service.DonthuocService;
import groub2.backend.service.ThuocService;
import java.util.Date;
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
@RequestMapping("/api/donthuoc")
public class DonthuocController {

    @Autowired
    DonthuocService donthuocService;
    @Autowired
    DonthuocDetailsService donthuocDetailsService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Donthuoc> read() {
        return donthuocService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity addCasher(@RequestBody ListHoaDonThuocDAO listHoaDonThuocDAO) {

        int totalmoney = 0;
        var listobj = listHoaDonThuocDAO.getListHDT();
        for (int i = 0; i < listobj.size(); i++) {
            totalmoney += (listobj.get(i).getPrice() * listobj.get(i).getQuantity());
        }

        Donthuoc newDonThuoc = new Donthuoc();
        newDonThuoc.setCreateAt(new Date());
        newDonThuoc.setName(listHoaDonThuocDAO.getName());
        newDonThuoc.setPhone(listHoaDonThuocDAO.getPhone());
        newDonThuoc.setTotalMoney(totalmoney);
        newDonThuoc.setCasherId(listHoaDonThuocDAO.getCasherId());
        
        var modelDonthuoc = donthuocService.saveDonthuoc(newDonThuoc);

        for (int i = 0; i < listobj.size(); i++) {
            Thuoc newThuoc = new Thuoc();
            newThuoc.setId(listobj.get(i).getThuocID());
            DonthuocDetails newDonthuocDetails = new DonthuocDetails();
            newDonthuocDetails.setDonthuocId(modelDonthuoc);
            newDonthuocDetails.setPrice(listobj.get(i).getPrice());
            newDonthuocDetails.setQuantity(listobj.get(i).getQuantity());
            newDonthuocDetails.setThuocId(newThuoc);
            donthuocDetailsService.saveDonthuoc(newDonthuocDetails);
        }

        var a = listHoaDonThuocDAO;
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
