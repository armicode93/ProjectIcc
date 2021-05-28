package be.iccbxl.pid.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="localities")

@AllArgsConstructor
public class Locality {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String postalCode;
    private String locality;

    @OneToMany( targetEntity=Location.class, mappedBy="locality" )
    private List<Location> locations = new ArrayList<>();
    //contien la liste de lieux de spectacle situee dans cette localite, pas de setter
    //pour ca, mais on va mettre de methode plus approprier comme addLocation

    protected Locality(){ }

    public List<Location> getLocations() {
        return locations;
    }
    public Locality addLocation(Location location) {
        if (!this.locations.contains(location)) {
            this.locations.add(location);
            location.setLocality(this);
        }
        return this;
    }

    public Locality removeLocation(Location location) {
        if (this.locations.contains(location)) {
            this.locations.remove(location);
            if (location.getLocality().equals(this)) {
                location.setLocality(null);
            }
        }
        return this;
        //ce 2 methode mis a jour la relation bilaterale du cote de  Location ajoutee ou retire
    }


    @Override
    public String toString() {
        return "Locality [id=" + id + ", postalCode=" + postalCode + ", locality=" + locality +
                "]";
    }
}


