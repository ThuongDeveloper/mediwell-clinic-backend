/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package groub2.backend.service;

import groub2.backend.entities.Rating;
import groub2.backend.res.RatingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ann
 */
@Service
public class RatingService {
    @Autowired
    RatingRepository res;

    public List<Rating> findAll() {
        return res.findAll();
    }

    public Rating getRatingById(Integer id) {
        return res.findById(id).orElse(null);
    }
    
    public void addRating(Rating newRating) {
        newRating.setCreateAt(new java.util.Date());
        res.save(newRating);
    }

    public Rating updateRating(Rating updatedRating) {
        Rating r = res.findById(updatedRating.getId()).orElse(null);
        if (r != null) {
            // Kiểm tra xem trường totalMoney và typeDoctorId có được cung cấp để cập nhật hay không
            if (updatedRating.getRating()!= null) {
                r.setRating(updatedRating.getRating());
            }
            if (updatedRating.getComment()!= null) {
                r.setComment(updatedRating.getComment());
            }

            // Cập nhật ngày tạo mới
            r.setCreateAt(new java.util.Date());

            // Lưu đối tượng Taophieukham đã cập nhật vào cơ sở dữ liệu
            return res.save(r);
        }
        return null;
    }

    public void deleteRating(Integer id) {
        res.deleteById(id);
    }
}
