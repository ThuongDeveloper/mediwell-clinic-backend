/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package groub2.backend.controller;

import groub2.backend.dto.FeedbackDAO;
import groub2.backend.entities.Feedback;
import groub2.backend.res.FeedbackRepository;
import groub2.backend.service.FeedbackService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author admin
 */
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;
    
    @Autowired
    FeedbackRepository resFeedbackRepository;
   

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Feedback> listFeedback() {
        return feedbackService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedback(@PathVariable Integer id) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        if (feedback != null) {
            return new ResponseEntity<>(feedback, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<Feedback> addFeedback(@RequestBody Feedback feedback) {
//        feedbackService.addFeedback(feedback);
//        return new ResponseEntity<>(feedback, HttpStatus.OK);
//    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Feedback> addFeedback(@RequestBody Feedback feedback) {
        feedback.setCreateAt(new Date());
        resFeedbackRepository.save(feedback);
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Feedback> updateFeedback(@PathVariable Integer id, @RequestBody Feedback updatedFeedback) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        if (feedback != null) {
            updatedFeedback.setId(id);
            Feedback updated = feedbackService.updateFeedback(updatedFeedback);
            if (updated != null) {
                return new ResponseEntity<>(updated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteFeedback(@PathVariable Integer id) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        if (feedback != null) {
            feedbackService.deleteFeedback(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
