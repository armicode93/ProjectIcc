package be.iccbxl.pid.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocalityService {
    @Autowired
    private LocalityRepository localityRepository;

    public List<Locality> getAllLocality() {
        List<Locality> localities = new ArrayList<>();

        localityRepository.findAll().forEach(localities::add);

        return localities;
    }

    public Locality get(String id){
        Long indice = (long) Integer.parseInt(id);
        Optional<Locality> locality = localityRepository.findById(indice);

        return locality.isPresent() ? locality.get() : null;
    }

    public void add(Locality locality){
        localityRepository.save(locality);
    }

    public void update(String id, Locality locality)
    {
        localityRepository.save(locality);
    }

    public void delete(String id) {
        Long indice=(long) Integer.parseInt(id);

        localityRepository.deleteById(indice);
    }



}
