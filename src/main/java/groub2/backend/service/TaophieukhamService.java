package groub2.backend.service;

import groub2.backend.entities.Casher;
import groub2.backend.entities.Taophieukham;
import groub2.backend.res.TaophieukhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaophieukhamService {

    @Autowired
    TaophieukhamRepository res;

    public List<Taophieukham> findAll() {
        return res.findAll();
    }

    public Taophieukham getTaophieukhamById(Integer id) {
        return res.findById(id).orElse(null);
    }

//    public boolean addTaophieukham(Taophieukham newTaophieukham) {
//        try {
//            newTaophieukham.setCreateAt(new java.util.Date());
//            res.save(newTaophieukham);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
    public void addTaophieukham(Taophieukham newTaophieukham) {
        newTaophieukham.setCreateAt(new java.util.Date());
        res.save(newTaophieukham);
    }

    public Taophieukham updateTaophieukham(Taophieukham updatedTaophieukham) {
        Taophieukham t = res.findById(updatedTaophieukham.getId()).orElse(null);
        if (t != null) {
            // Kiểm tra xem trường totalMoney và typeDoctorId có được cung cấp để cập nhật hay không
            if (updatedTaophieukham.getTotalMoney() != null) {
                t.setTotalMoney(updatedTaophieukham.getTotalMoney());
            }
            if (updatedTaophieukham.getTypeDoctorId() != null) {
                t.setTypeDoctorId(updatedTaophieukham.getTypeDoctorId());
            }

            // Cập nhật ngày tạo mới
            t.setCreateAt(new java.util.Date());

            // Lưu đối tượng Taophieukham đã cập nhật vào cơ sở dữ liệu
            return res.save(t);
        }
        return null;
//        res.save(updatedTaophieukham);
//        return updatedTaophieukham;
    }

    public void deleteTaophieukham(Integer id) {
        res.deleteById(id);
    }
}
