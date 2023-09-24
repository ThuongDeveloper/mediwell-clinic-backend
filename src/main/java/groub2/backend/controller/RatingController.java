/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package groub2.backend.controller;

import groub2.backend.dto.RatingDAO;
import groub2.backend.dto.listToathuocDAO;
import groub2.backend.entities.Rating;
import groub2.backend.entities.Toathuoc;
import groub2.backend.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingController {
    @Autowired
    RatingService ratingService;

    @GetMapping()
    public ResponseEntity<List<Rating>> listRating() {
        List<Rating> ratings = ratingService.findAll();
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rating> get(@PathVariable Integer id) {
        Rating rating = ratingService.getRatingById(id);
        if (rating != null) {
            return new ResponseEntity<>(rating, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> addRating(@Valid @RequestBody Rating rating) {
        if (rating.getId() != null) {
            return new ResponseEntity<>("ID must be null for new rating.", HttpStatus.BAD_REQUEST);
        }
        ratingService.addRating(rating);
        return new ResponseEntity<>(rating, HttpStatus.CREATED);
    }

    @PostMapping("/createRating")
    public ResponseEntity<?> addRatingPatient(@RequestBody RatingDAO ratingDAO) {
        Rating newRating = new Rating();
        newRating.setCreateAt(new Date());
        newRating.setRating(ratingDAO.getRating());
        newRating.setComment(ratingDAO.getComment());
        newRating.setPatientId(ratingDAO.getPatient_id());
        newRating.setDoctorId(ratingDAO.getDoctor_id());

        ratingService.addRating(newRating);

        return new ResponseEntity<>(newRating, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateRating(@PathVariable Integer id, @Valid @RequestBody Rating updatedRating) {
        Rating existingRating = ratingService.getRatingById(id);
        if (existingRating == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        // Perform updates, e.g., validation checks, before saving
        existingRating.setRating(updatedRating.getRating());
        existingRating.setComment(updatedRating.getComment());

        Rating updated = ratingService.updateRating(existingRating);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRating(@PathVariable int id) {
        Rating rating = ratingService.getRatingById(id);
        if (rating == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        ratingService.deleteRating(id);
        return ResponseEntity.noContent().build();
    }
}
