/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package groub2.backend.res;

import groub2.backend.entities.Casher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Ann
 */
public interface CasherRepository extends JpaRepository<Casher, Integer> {
    Casher findByName(String name);
    boolean existsByEmail(String email);
    @Query("UPDATE Casher SET password = :newPassword WHERE id = :id")
    int changePassword(@Param(value = "id") Integer id, @Param(value = "newPassword") String newPassword);
}
