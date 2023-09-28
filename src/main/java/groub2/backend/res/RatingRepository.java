/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package groub2.backend.res;

import groub2.backend.entities.Rating;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Ann
 */
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    
       @Query("SELECT r FROM Rating r WHERE r.createAt >= :startDate AND r.createAt <= :endDate ORDER BY r.createAt ASC")
    List<Rating> findRatingsByDateAndIndexDesign(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);
       

}
