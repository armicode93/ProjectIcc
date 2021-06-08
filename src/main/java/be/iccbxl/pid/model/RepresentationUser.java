package be.iccbxl.pid.model;


import javax.persistence.*;

@Entity
@Table(name="representation_user")
public class    RepresentationUser {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "representation_id")
    private Representation representation ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User  user ;

    private Long places;

    protected RepresentationUser() { }

    public RepresentationUser(Representation representation, User user, Long places){
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

    @Override
    public String toString() {
        return "RepresentationUser{" +
                "id=" + id +
                ", representation=" + representation +
                ", user=" + user +
                ", places=" + places +
                '}';
    }
}
