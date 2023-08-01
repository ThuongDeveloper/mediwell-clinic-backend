/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package groub2.backend.service;

import groub2.backend.entities.Doctor;
import groub2.backend.entities.Thuoc;
import groub2.backend.res.ThuocRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dochi
 */
@Service
public class ThuocService {

    @Autowired
    ThuocRepository res;

    public List<Thuoc> getAll() {
        return res.findAll();
    }

    public boolean saveThuoc(Thuoc thuoc) {

        try {
            res.save(thuoc);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<Thuoc> getOneThuocById(int id) {
        return res.findById(id);

    }

    public Thuoc editTypeThuoc(Thuoc thuoc) {
        res.save(thuoc);
        return thuoc;
    }

    public boolean deleteThuocId(int id) {
        try {
            res.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
