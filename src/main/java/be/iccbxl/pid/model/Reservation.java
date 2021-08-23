package be.iccbxl.pid.model;

import javax.persistence.*;

@Entity
@Table(name="reservations")

public class Reservation {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name="representation_id", nullable = false)
    private Representation representation ;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user ;

    private Long places ;

    public Reservation() {
    }

    public Reservation(Representation representation, User user, Long places) {
        this.representation = representation;
        this.user = user;
        this.places = places;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Representation getRepresentation() {
        return representation;
    }

    public void setRepresentation(Representation representation) {
        this.representation = representation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getPlaces() {
        return places;
    }

    public void setPlaces(Long places) {
        this.places = places;
    }

}
