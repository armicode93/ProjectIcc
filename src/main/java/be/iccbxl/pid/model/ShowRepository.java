package be.iccbxl.pid.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShowRepository extends CrudRepository<Show, Long> {
    Show findBySlug(String slug);
    Show findByTitre(String titre);
    List<Show> findByLocation(Location location);
}
