/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package groub2.backend.res;

import groub2.backend.entities.Casher;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ann
 */
public interface CasherRepository extends JpaRepository<Casher, Integer> {
    Casher findByName(String name);
    boolean existsByEmail(String email);
}
