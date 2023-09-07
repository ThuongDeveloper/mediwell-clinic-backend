/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package groub2.backend.res;

import groub2.backend.entities.DonthuocDetails;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
/**
 *
 * @author DELL
 */
public interface DonThuocDetailsRepository extends JpaRepository<DonthuocDetails, Integer> {
  //   @Query("SELECT p FROM donthuoc_details p WHERE p.donthuoc_id = :donthuocId")
  //  List<DonthuocDetails> getListbyID(@PathVariable("donthuocId") int donthuocId);
}
