package be.iccbxl.pid.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="representations")

public class Representation {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    //metto direttamente la relazioen con show ,xk nel diagramme de classe ,c'est show_id ora
    @ManyToOne
    @JoinColumn(name="show_id", nullable = false)
    private Show show;

    @ManyToMany
    @JoinTable(
            name="reservations",
            joinColumns=@JoinColumn(name="representation_id"), //seguire cosa c] scritto nel diagramme di classe
            inverseJoinColumns= @JoinColumn(name = "user_id"))
    private List<User> users = new ArrayList<>();

    //Date creation representation

    private LocalDateTime when;

    @ManyToOne
    @JoinColumn(name="location_id", nullable=true)
    private Location location;

    public Representation() {
    }

    public Representation(Show show, LocalDateTime when, Location location) {

        this.show = show;
        this.when = when;
        this.location = location;
    }

    public Long getId() {       // non ho capito il xk non lha messo nel constructeur
        return id;
    }


    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public LocalDateTime getWhen() {
        return when;
    }

    public void setWhen(LocalDateTime when) {
        this.when = when;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<User> getUsers() {
        return users;
    }
    public Representation addUser(User user) {
        if(!this.users.contains(user)) {
            this.users.add(user);
            user.addRepresentation(this);
        }
        return this;
    }
    public Representation removeUser(User user) {
        if(this.users.contains(user)) {
            this.users.remove(user);
            user.getRepresentations().remove(this);
        }
        return this;
    }

    @Override
    public String toString() {
        return "Representation [id=" + id + ", show=" + show + ", when=" + when
                + ", location=" + location + "]";
    }



}
