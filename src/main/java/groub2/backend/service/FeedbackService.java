/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package groub2.backend.service;

import groub2.backend.dto.FeedbackDAO;
import groub2.backend.entities.Feedback;
import groub2.backend.entities.Patient;
import groub2.backend.res.FeedbackRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class FeedbackService {
    @Autowired
    FeedbackRepository res;
    
    @Autowired
    PatientService patientService;

    public List<Feedback> findAll() {
        return res.findAll();
    }

    public Feedback getFeedbackById(Integer id) {
        return res.findById(id).orElse(null);
    }

//    public void addFeedback(Feedback newFeedback) {
//        newFeedback.setCreateAt(new java.util.Date());
//        res.save(newFeedback);
//    }
    
    public Feedback addFeedback(FeedbackDAO feedbackDAO) {
//        Xác định feedback của patient nào
        Patient patient = patientService.getPatientById(feedbackDAO.getPatientId());
//        Tạo đối tượng feed mới
        Feedback newFeed = new Feedback();
        newFeed.setContent(feedbackDAO.getContent());
        newFeed.setStatus(false);
        newFeed.setTitle(feedbackDAO.getTitle());
        newFeed.setPatientId(patient);
        newFeed.setCreateAt(new java.util.Date());
//        Lưu newfeed mới và trả về feedback vừa thêm
        return res.save(newFeed);
    }

    public Feedback updateFeedback(Feedback updatedFeedback) {
        Feedback feedback = res.findById(updatedFeedback.getId()).orElse(null);
        if (feedback != null) {
            // Kiểm tra xem các trường có được cung cấp để cập nhật hay không
            if (updatedFeedback.getTitle()!= null) {
                feedback.setTitle(updatedFeedback.getTitle());
            }
            if (updatedFeedback.getContent()!= null) {
                feedback.setContent(updatedFeedback.getContent());
            }
            if (updatedFeedback.getStatus()!= null) {
                feedback.setStatus(updatedFeedback.getStatus());
            }
            if (updatedFeedback.getPatientId()!= null) {
                feedback.setPatientId(updatedFeedback.getPatientId());
            }

            // Cập nhật ngày tạo mới
            feedback.setCreateAt(new java.util.Date());

            // Lưu đối tượng Casher đã cập nhật vào cơ sở dữ liệu
            return res.save(feedback);
        }
        return null;
    }

    public void deleteFeedback(Integer id) {
        res.deleteById(id);
    }
}
