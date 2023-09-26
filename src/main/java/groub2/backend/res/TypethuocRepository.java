/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package groub2.backend.res;

/**
 *
 * @author dochi
 */
import groub2.backend.entities.Typethuoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypethuocRepository extends JpaRepository<Typethuoc, Integer> {
    Typethuoc findByName(String name);

}