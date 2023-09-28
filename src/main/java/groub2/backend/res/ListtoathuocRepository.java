/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package groub2.backend.res;

import groub2.backend.entities.Taophieukham;
import groub2.backend.entities.Toathuoc;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Ann
 */
public interface ListtoathuocRepository extends JpaRepository<Toathuoc, Integer> {

    @Query("SELECT t FROM Toathuoc t WHERE CONVERT(DATE, t.createAt) = CONVERT(DATE, GETDATE())")
    List<Toathuoc> findAllByCreateAtToday();

}
