package be.iccbxl.pid.model;

import javax.persistence.*;
import java.time.LocalDateTime;


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

    /*@Override
    public String toString() {
        return "Representation [id=" + id + ", show=" + show + ", when=" + when
                + ", location=" + location + "]";
    }

     */

}
