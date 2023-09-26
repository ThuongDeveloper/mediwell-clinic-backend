package groub2.backend.res;

import groub2.backend.entities.Donvi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonviRepository extends JpaRepository<Donvi, Integer> {
    Donvi findByName(String name);

}