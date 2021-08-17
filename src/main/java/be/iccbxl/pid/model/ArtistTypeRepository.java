package be.iccbxl.pid.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistTypeRepository extends JpaRepository<ArtistType, Long> {
    List<ArtistType> findByType(Type type);
    List<ArtistType> findByArtist(Artist artist);

    ArtistType findById(long id);

}
