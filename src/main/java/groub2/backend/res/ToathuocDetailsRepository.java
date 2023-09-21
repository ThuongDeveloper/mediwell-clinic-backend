package groub2.backend.res;

import groub2.backend.entities.ToathuocDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToathuocDetailsRepository extends JpaRepository<ToathuocDetails, Integer> {
    List<ToathuocDetails> findByToathuocId(Integer toathuocId);
}