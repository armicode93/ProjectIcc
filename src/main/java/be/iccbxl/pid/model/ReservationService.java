package be.iccbxl.pid.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation findOneById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }

    //problema
    /*public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

     */
}
