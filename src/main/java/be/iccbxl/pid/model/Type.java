package be.iccbxl.pid.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="types")
@NoArgsConstructor
@AllArgsConstructor

public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;

    @ManyToMany
    @JoinTable(
            name="artist_type", //name table de jointure
            joinColumns= @JoinColumn(name="type_id"), //name column de jointure
            inverseJoinColumns= @JoinColumn(name = "artist_id")) //name deuxieme column de jointure
    private List<Artist> artists= new ArrayList<>();

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
}

