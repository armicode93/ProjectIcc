package be.iccbxl.pid.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="types")
@NoArgsConstructor
@AllArgsConstructor


public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotEmpty(message = "Type name value is required and can t be empty")
    @Size(min=3, max=60, message ="Type name should have at least 2 characters and max 60 characters")
    @Column(name="type")
    private String typeName;

    //table de jointure
    @ManyToMany
    @JoinTable(
            name="artist_type", //name table de jointure
            joinColumns= @JoinColumn(name="type_id"), //name column de jointure
            inverseJoinColumns= @JoinColumn(name = "artist_id")) //name deuxieme column de jointure
    private List<Artist> artists= new ArrayList<>();

    public Type(String type) {
        this.typeName = type;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public Type addArtist(Artist artist) {
        if(!this.artists.contains(artist)) {
            this.artists.add(artist);
            artist.addType(this);
        }
        return this;
    }
    public Type removeType(Artist artist) {
        if(this.artists.contains(artist)) {
            this.artists.remove(artist);
            artist.getTypes().remove(this);
        }
        return this;
    }
    public Long getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String type) {
        this.typeName = type;
    }
    @Override
    public String toString() {
        return "Type [id=" + id + ", type=" + typeName + "]";
    }
}