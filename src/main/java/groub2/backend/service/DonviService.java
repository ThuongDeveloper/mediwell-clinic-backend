package groub2.backend.service;

import groub2.backend.entities.Donvi;
import groub2.backend.entities.Typethuoc;
import groub2.backend.res.DonviRepository;
import groub2.backend.res.TypethuocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonviService {
    @Autowired
    DonviRepository res;

    public List<Donvi> getAll() {
        return res.findAll();
    }

    public boolean saveDonvi(Donvi donvi) {

        try {
            res.save(donvi);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    public Optional<Donvi> findDonvibyID(int id){
        return res.findById(id);

    }

    public void editDonvi(Donvi donvi){
        res.save(donvi);
    }
    public boolean deleteDonviId(int id){
        try {
            res.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    public boolean isDonviNameExists(String name) {
        // Sử dụng Spring Data JPA để kiểm tra xem tên đã tồn tại trong cơ sở dữ liệu chưa
        // Đây là một ví dụ giả định về cách bạn có thể triển khai nó. Sự triển khai cụ thể có thể khác nhau tùy vào cơ sở dữ liệu của bạn.

        Donvi existingDonvi = res.findByName(name);
        return existingDonvi != null;
    }

}