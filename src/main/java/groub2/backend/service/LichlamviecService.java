/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package groub2.backend.service;

import groub2.backend.entities.Casher;
import groub2.backend.entities.Doctor;
import groub2.backend.entities.Lichlamviec;
import groub2.backend.res.CasherRepository;
import groub2.backend.res.LichlamviecRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hokim
 */
@Service
public class LichlamviecService {
    
    @Autowired
    LichlamviecRepository res;

    public List<Lichlamviec> findAll() {
        return res.findAll();
    }

    public Lichlamviec getLichById(Integer id) {
        return res.findById(id).orElse(null);
    }

    public void addLich(Lichlamviec newLich) {
        newLich.setCreateAt(new java.util.Date());
        res.save(newLich);
    }

    public Lichlamviec updateLich(Lichlamviec updatedLich) {
        Lichlamviec lich = res.findById(updatedLich.getId()).orElse(null);
        if (lich != null) {
            // Kiểm tra xem các trường có được cung cấp để cập nhật hay không
            if (updatedLich.getThu()!= null) {
                lich.setThu(updatedLich.getThu());
            }
            if (updatedLich.getDate()!= null) {
                lich.setDate(updatedLich.getDate());
            }
            if (updatedLich.getDoctorId()!= null) {
                lich.setDoctorId(updatedLich.getDoctorId());
            }
            if (updatedLich.getStarttime()!= null) {
                lich.setStarttime(updatedLich.getStarttime());
            }
            if (updatedLich.getEndtime()!= null) {
                lich.setEndtime(updatedLich.getEndtime());
            }

            // Cập nhật ngày tạo mới
            lich.setCreateAt(new java.util.Date());

            // Lưu đối tượng Casher đã cập nhật vào cơ sở dữ liệu
            return res.save(lich);
        }
        return null;
    }
     public List<Lichlamviec> getLichByDoctorID(Doctor doctorID) {
        return res.findByDoctorId(doctorID);
    }

    public void deleteLich(Integer id) {
        res.deleteById(id);
    }
}
