package groub2.backend.res;

import groub2.backend.entities.Doctor;
import groub2.backend.entities.TypeDoctor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author DELL
 */
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    @Query("SELECT d, AVG(r.rating) AS average_rating FROM Doctor d LEFT JOIN Rating r ON d.id = r.doctorId.id GROUP BY d.id, d.name, d.role, d.typeDoctorId, d.email, d.address, d.gender, d.image, d.createAt, d.password, d.username")
    List<Object> getDoctorsWithAverageRating();

    @Query("SELECT d, AVG(r.rating) AS average_rating FROM Doctor d LEFT JOIN Rating r ON d.id = r.doctorId.id WHERE d.name LIKE %:name% GROUP BY d.id, d.name, d.role, d.typeDoctorId, d.email, d.address, d.gender, d.image, d.createAt, d.password, d.username")
    List<Object> getDoctorsWithAverageRatingAndFilterByName(@Param("name") String name);

    List<Doctor> findByTypeDoctorId(TypeDoctor type);

    boolean existsByEmail(String email);

}
