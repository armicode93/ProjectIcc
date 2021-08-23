package be.iccbxl.pid.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    Reservation findById(long id);
    List<Reservation> findByRepresentation(Representation representation);
    List<Reservation> findByUser(User user);
}
