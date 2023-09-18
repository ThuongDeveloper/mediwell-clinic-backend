package groub2.backend.service;

import groub2.backend.entities.Toathuoc;
import groub2.backend.res.ToathuocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToathuocService {
    @Autowired
    ToathuocRepository res;
    public List<Toathuoc> getAll() {
        return res.findAll();
    }

    public Toathuoc saveToathuoc(Toathuoc toathuoc) {

        try {
            res.save(toathuoc);
            return toathuoc;
        } catch (Exception e) {
            return null;
        }
    }




}
