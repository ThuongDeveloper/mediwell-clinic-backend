/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package groub2.backend.res;

import groub2.backend.entities.Doctor;
import groub2.backend.entities.Lichlamviec;
import groub2.backend.entities.Patient;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author hokim
 */
public interface LichlamviecRepository extends JpaRepository<Lichlamviec, Integer> {
     @Query("SELECT c FROM Patient c WHERE c.email = :email")
    public Patient SearchByEmail(@PathVariable("email") String email);
     public List<Lichlamviec> findByDoctorId(Doctor id);
}
