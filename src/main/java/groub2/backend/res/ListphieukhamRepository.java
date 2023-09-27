package groub2.backend.res;

import groub2.backend.entities.Taophieukham;
import groub2.backend.entities.TypeDoctor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface ListphieukhamRepository extends JpaRepository<Taophieukham, Integer> {


    @Query("SELECT p FROM Taophieukham p WHERE p.typeDoctorId = :typeDoctor AND CAST(p.createAt AS date) = :createAt")
    List<Taophieukham> findByTypeDoctorAndCreateAt(@Param("typeDoctor") TypeDoctor typeDoctor, @Param("createAt") Date createAt);

}