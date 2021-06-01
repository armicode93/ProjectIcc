package be.iccbxl.pid.model;


import com.github.slugify.Slugify;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String slug;
    private String title;
    private String description;

    @Column(name="poster_url")
    private String posterUrl;
    /**
     * Lieu de création du spectacle
     */
    @ManyToOne
    @JoinColumn(name="location_id", nullable=true) // il problema era qua
    private Location location;  //in realta nella table era location_id,siccome è l'id del location scrivero cosi
    private boolean bookable;
    private double price;

 //  prendere gli artist che collaborano in un determinato spettacolo
    @ManyToMany(mappedBy = "shows")
    private List<ArtistType> artistTypes = new ArrayList<>();
    /**
     * Date de création du spectacle
     */

    @Column(name="created_at") //date creation spectacle
    private LocalDateTime createdAt;

    /**
     * Date de modification du spectacle
     */
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(targetEntity = Representation.class, mappedBy ="show")
    private List<Representation> representations = new ArrayList<>();

    public List<Representation> getRepresentations()
    {
        return representations;
    }
    public Show addRepresentation(Representation representation)
    {
        if(!this.representations.contains(representation)){
            this.representations.add(representation);
            representation.setShow(this);
        }
        return this;
    }
    public Show removeRepresentation(Representation representation)
    {
        if(this.representations.contains(representation))
        {
            this.representations.remove(representation);
            if(representation.getLocation().equals(this)){
                representation.setLocation(null);
            }
        }
        return this;
    }

    public Show() {
    }

    public Show(String title, String description, String posterUrl, Location location, boolean bookable, double price) {
        Slugify slg = new Slugify();
        this.slug = slg.slugify(title);

        this.title = title;
        this.description = description;
        this.posterUrl = posterUrl;
        this.location = location;
        this.bookable = bookable;
        this.price = price;

        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
    }
    public Long getId() {
        return id;
    }

    public String getSlug() {

        return slug;
    }

    private void setSlug(String slug) {

        this.slug = slug;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;

        Slugify slg = new Slugify();
        this.setSlug(slg.slugify(title));
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public String getPosterUrl() {

        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location.removeShow(this); //déménager de l’ancien lieu
        this.location = location;
        this.location.addShow(this); //emménager dans le nouveau lieu
        //mettre a jour la liste des shows et de consequence, eliminere le spectacle de
        //l'ancien lieux et ajout dans le nouveau
    }

    public boolean isBookable() {

        return bookable;
    }

    public void setBookable(boolean bookable) {

        this.bookable = bookable;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<ArtistType> getArtistTypes() {
        return artistTypes;
    }
    public Show addArtistType(ArtistType artistType)
    {
        if(!this.artistTypes.contains(artistType)) {
            this.artistTypes.add(artistType);
            artistType.addShow(this);
        }
        return this;
    }
    public Show removeArtistType(ArtistType artistType)
    {
        if(this.artistTypes.contains(artistType)) {
            this.artistTypes.remove(artistType);
            artistType.getShows().remove(this);
        }
        return this;
    }

    @Override
    public String toString() {
        return "Show [id=" + id + ", slug=" + slug + ", title=" + title
                + ", description=" + description + ", posterUrl=" + posterUrl + ", location="
                + location + ", bookable=" + bookable + ", price=" + price
                + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt+
                 ", representations=" + representations.size() + "]";
    }


}
