package model;

import org.springframework.data.repository.CrudRepository;    //definire le methode d acces a la BD

import java.util.List;


public interface ArtistRepository extends CrudRepository <Artist, Long> {
        List<Artist> findByLastname(String lastname);

        Artist findById(long id);

    }

