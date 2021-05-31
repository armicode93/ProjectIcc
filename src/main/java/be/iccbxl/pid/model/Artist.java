package be.iccbxl.pid.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data //no setter no getter ,with this annotation is inside
@Entity
@Table(name="artists")
@AllArgsConstructor

public class Artist {
    @Id //clef primaire, identifiant unique
    @GeneratedValue(strategy=GenerationType.AUTO) //auto generate
    //ou GenerationType.IDENTITY
    private Long id;
    private String firstname;
    private String lastname;

    @ManyToMany(mappedBy="artists")
    private List<Type> types = new ArrayList<>();

    protected Artist() {}


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
