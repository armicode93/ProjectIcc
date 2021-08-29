package be.iccbxl.pid.model;

import lombok.Data;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="artist_type")

public class ArtistType  {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;
    @ManyToOne
    @JoinColumn(name="artist_id", nullable=false)
    private Artist artist;

    @ManyToOne
    @JoinColumn(name="type_id" , nullable=false)
    private Type type;
    // ci permettera che a partire da uno spettacolo show, di rintracciare l'artista e il tipo

    @ManyToMany(targetEntity = Show.class)
    @JoinTable(
            name="artist_type_show", //name table de jointure
            joinColumns= @JoinColumn(name="artist_type_id"), //name column de jointure
            inverseJoinColumns= @JoinColumn(name = "show_id"))
    private List<Show> shows = new ArrayList<>();

    public ArtistType (){}

    public ArtistType(Artist artist, Type type) {
        this.artist = artist;
        this.type = type;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist( Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Show> getShows() {
        return shows;
    }

    public ArtistType addShow(Show show) {
        if(!this.shows.contains(show)) {
            this.shows.add(show);
            show.addArtistType(this);
        }
        return this;
    }
    public ArtistType removeShow(Show show) {
        if(this.shows.contains(show)) {
            this.shows.remove(show);
            show.getArtistTypes().remove(this);
        }
        return this;
    }

    @Override
    public String toString() {
        return "ArtistType [id=" + id + ", artist=" + artist + ", type=" + type
                + ", shows=" + shows + "]";
    }
}
