package be.iccbxl.pid.model;


import com.github.slugify.Slugify;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String slug;
    private String titre;
    private String description;

    @Column(name="poster_url")
    private String posterUrl;
    /**
     * Lieu de création du spectacle
     */
    @ManyToOne
    @JoinColumn(name="location_id", nullable=false)
    private Location location;  //in realta nella table era location_id,siccome è l'id del location scrivero cosi
    private boolean bookable;
    private double price;

    /**
     * Date de création du spectacle
     */

    @Column(name="created_at")
    private LocalDateTime createdAt;

    /**
     * Date de modification du spectacle
     */
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    public Show() {
    }

    public Show(String titre, String description, String posterUrl, Location location, boolean bookable, double price) {
        Slugify slg = new Slugify();
        this.slug = slg.slugify(titre);

        this.titre = titre;
        this.description = description;
        this.posterUrl = posterUrl;
        this.location = location;
        this.bookable = bookable;
        this.price = price;

        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
    }

    public String getSlug() {
        return slug;
    }

    private void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;

        Slugify slg = new Slugify();
        this.setSlug(slg.slugify(titre));
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

    @Override
    public String toString() {
        return "Show [id=" + id + ", slug=" + slug + ", title=" + titre
                + ", description=" + description + ", posterUrl=" + posterUrl + ", location="
                + location + ", bookable=" + bookable + ", price=" + price
                + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }
}
