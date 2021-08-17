package be.iccbxl.pid.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistTypeService {
    @Autowired
    private ArtistTypeRepository artistTypeRepository;
    public ArtistType save(ArtistType artistType) {
        return artistTypeRepository.save(artistType);
    }

    public ArtistType findOneById(Long id) {
        return artistTypeRepository.findById(id).orElse(null);
    }

    public List<ArtistType> findAll() {
        return artistTypeRepository.findAll();
    }

}
