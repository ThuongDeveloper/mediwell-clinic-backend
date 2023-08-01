/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package groub2.backend.res;

import groub2.backend.entities.Taophieukham;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ann
 */
public interface TaophieukhamRepository extends JpaRepository<Taophieukham, Integer> {
    Taophieukham findByName(String name);
}
