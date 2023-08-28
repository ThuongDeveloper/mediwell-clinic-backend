package groub2.backend.res;

import groub2.backend.entities.Donthuoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface DonthuocRepository extends JpaRepository<Donthuoc, Integer> {
}
