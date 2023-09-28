/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package groub2.backend.res;

import groub2.backend.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author admin
 * 
 */
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    boolean existsByEmail(String email);

    Patient findByEmail(String email);

    boolean existsByEmailAndPhone(String email, String phone);

}
