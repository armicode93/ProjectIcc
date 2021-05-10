package be.iccbxl.pid.model;

import javax.persistence.*;
import com.github.slugify.Slugify;

@Entity
@Table(name="locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true) //Contrainte de unicite pour le champs slug
    //valeur unique
    //column utiliser aussi quand le nom de la colonne different de la table
    //avec name=
    private String slug;

    private String designation;
    private String adress;

    @ManyToOne
    @JoinColumn(name="locality_id", nullable = false)
    //on indique quelle est la clef entranger ici ì
    // nullable = false ca veut dire que accepte pas valeur NULL
    private Locality locality;

    private String website;
    private String phone;

    public Location() {
    }

    public Location(String slug, String designation, String adress, Locality locality, String website, String phone) {
        Slugify slg = new Slugify();


        this.slug = slg.slugify(designation);
        this.designation = designation;
        this.adress = adress;
        this.locality = locality;
        this.website = website;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    private void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
        Slugify slg = new Slugify();
        this.setSlug(slg.slugify(designation));

    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Locality getLocality() {
        return locality;
    }

    public void setLocality(Locality locality) {
        this.locality.removeLocation(this); //déménager de l’ancienne localité
        this.locality = locality;
        this.locality.addLocation(this);    //emménager dans la nouvelle localité
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Location [id=" + id + ", slug=" + slug + ", designation=" + designation
                + ", address=" + adress + ", locality=" + locality + ", website="
                + website + ", phone=" + phone + "]";
    }
}

