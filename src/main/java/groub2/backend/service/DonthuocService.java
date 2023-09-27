package groub2.backend.service;

import groub2.backend.entities.Donthuoc;
import groub2.backend.res.DonthuocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonthuocService {

    @Autowired
    DonthuocRepository res;

    public List<Donthuoc> getAll() {
        return res.findAll();
    }

    public Donthuoc getDonThuocId(int id) {
        return res.findById(id).orElse(null);
    }

    public Donthuoc saveDonthuoc(Donthuoc donthuoc) {

        try {
            res.save(donthuoc);
            return donthuoc;
        } catch (Exception e) {
            return null;
        }
    }

}
