package be.iccbxl.pid.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Reservation> getAll() {
        List<Reservation> reservations = new ArrayList<>();

        reservationRepository.findAll().forEach(reservations::add);

        return reservations;
    }
   /* public List<Reservation> getAllTri() {
        List<Reservation> reservations = new ArrayList<>();
        @Query("select distinct  show0_.id as id1_9_0_, show0_.bookable as bookable2_9_0_, show0_.created_at as created_3_9_0_, show0_.description as descript4_9_0_, show0_.location_id as locatio10_9_0_, show0_.poster_url as poster_u5_9_0_, show0_.price as price6_9_0_, show0_.slug as slug7_9_0_, show0_.title as title8_9_0_, show0_.updated_at as updated_9_9_0_, location1_.id as id1_4_1_, location1_.address as address2_4_1_, location1_.designation as designat3_4_1_, location1_.locality_id as locality7_4_1_, location1_.phone as phone4_4_1_, location1_.slug as slug5_4_1_, location1_.website as website6_4_1_, locality2_.id as id1_3_2_, locality2_.locality as locality2_3_2_, locality2_.postal_code as postal_c3_3_2_ from shows show0_ left outer join locations location1_ on show0_.location_id=location1_.id left outer join localities locality2_ on location1_.locality_id=locality2_.id")
                reservationRepository.findAll().forEach(reservations::add);

        return reservations;
    }

    */

    public List<Reservation> getFromRepresentation(Representation representation) {
        return reservationRepository.findByRepresentation(representation);
    }
    public List<Reservation> getFromUser(User user) {
        return reservationRepository.findByUser(user);
    }

    public Reservation getReservation(String id)
    {
        int indice = Integer.parseInt(id);

        return reservationRepository.findById(indice);
    }
}
