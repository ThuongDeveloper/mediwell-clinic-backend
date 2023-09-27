package groub2.backend.service;

import groub2.backend.entities.Taophieukham;
import groub2.backend.entities.TypeDoctor;
import groub2.backend.res.ListphieukhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class ListphieukhamService {
    @Autowired
    private ListphieukhamRepository res;
    public List<Taophieukham> getTaophieukhamByTypeDoctorAndCreateAt(TypeDoctor typedoctor, Date createAt) {
        return res.findByTypeDoctorAndCreateAt(typedoctor, createAt);
    }
}
