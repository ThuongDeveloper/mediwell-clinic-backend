/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package groub2.backend.service;

import groub2.backend.entities.Donthuoc;
import groub2.backend.entities.DonthuocDetails;
import groub2.backend.entities.ToathuocDetails;
import groub2.backend.res.DonThuocDetailsRepository;
import groub2.backend.res.DonthuocRepository;
import java.util.List;

import groub2.backend.res.ToathuocDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToathuocDetailsService {
    @Autowired
    ToathuocDetailsRepository res;
    public List<ToathuocDetails> getAll() {
        return res.findAll() ;
    }



    public boolean saveToathuoc(ToathuocDetails toathuocDetails) {

        try {
            res.save(toathuocDetails);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<ToathuocDetails> getToathuocDetailsByToathuocId(Integer toathuocId) {
        return res.findByToathuocId(toathuocId);
    }

}
