package be.iccbxl.pid.model;


import lombok.*;
import javax.validation.constraints.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter //no setter no getter ,with this annotation is inside
@Setter
@Entity
@Table(name="artists")


public class Artist {
    @Id //clef primaire, identifiant unique
    @GeneratedValue(strategy=GenerationType.AUTO) //auto generate
    //ou GenerationType.IDENTITY


    private Long id;

    @NotEmpty(message = "{NotEmpty.Artist.Firstname}")
    @Size(min=3, max=60 , message = "{Size.Artist.Firstname}")
    private String firstname;

    @NotEmpty(message = "{NotEmpty.Artist.Lastname}")
    @Size(min=3, max=60, message = "{Size.Artist.Firstname}")
    private String lastname;

    @ManyToMany(mappedBy="artists")
    private List<Type> types = new ArrayList<>();

    protected Artist() {}

    public Artist(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }


    public List<Type> getTypes()
    {
        return types;
    }
    public Artist addType(Type type)
    {
        if(!this.types.contains(type)) {
            this.types.add(type);
            type.addArtist(this);
        }
        return this;

    }
    public Artist removeType(Type type)
    {
        if(this.types.contains(type)) {
            this.types.remove(type);
            type.getArtists().remove(this);
        }
        return this;

    }


}
