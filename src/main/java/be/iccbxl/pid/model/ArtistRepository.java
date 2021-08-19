package be.iccbxl.pid.model;

import org.springframework.data.repository.CrudRepository;    //definire le methode d acces a la BD
import org.springframework.stereotype.Repository;

import java.util.List;

//or extends @JpaRepository and add @Repository annotation
public interface ArtistRepository extends CrudRepository <Artist, Long> {
        List<Artist> findByLastname(String lastname);
        List<Artist> findByFirstname(String firstname);

        Artist findById(long id);

    }

