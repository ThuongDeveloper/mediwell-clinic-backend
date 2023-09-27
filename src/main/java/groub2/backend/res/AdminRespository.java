/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package groub2.backend.res;

import groub2.backend.entities.Admin;
import groub2.backend.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author DELL
 */
public interface AdminRespository extends JpaRepository<Admin, Integer> {
    boolean existsByEmail(String email);
         @Query("SELECT c FROM Admin c WHERE c.email = :email")
    public Admin SearchByEmail(@PathVariable("email") String email);
}
