package be.iccbxl.pid.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.validation.constraints.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="artists")


public class Artist {
    @Id //clef primaire, identifiant unique
    @GeneratedValue(strategy=GenerationType.IDENTITY) //auto generate
    //ou GenerationType.IDENTITY


    private Long id;


    @NotEmpty(message = "Firstname value is required and can t be empty")
    @Size(min = 2, max = 60 , message = "Firstname should have at least 2 characters and max 60 characters")
    private String firstname;


    @NotEmpty(message = "Lastname value is required and can t be empty")
    @Size(min = 2, max = 60, message = "Lastname should have at least 2 characters and max 60 characters")
    private String lastname;

    @ManyToMany(mappedBy="artists")
    private List<Type> types = new ArrayList<>();

    public Artist() {}

    public Artist(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    @Override
    public String toString() {
        return firstname + " " + lastname;
    }


}