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
 */
public interface PatientRepository extends JpaRepository<Patient, Integer> {

<<<<<<< HEAD
=======
    

>>>>>>> 6e241fa24707c1be31bf764598d2bd859e9f8906
    boolean existsByEmail(String email);

    Patient findByEmail(String email);

    boolean existsByEmailAndPhone(String email, String phone);

}
