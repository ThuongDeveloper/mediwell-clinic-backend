/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package groub2.backend.res;

import groub2.backend.entities.Appointment;
import groub2.backend.entities.Patient;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author hokim
 */
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByPatientId(Patient patientId);
}
