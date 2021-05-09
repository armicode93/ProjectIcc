package be.iccbxl.pid.model;

import org.apache.tomcat.jni.Local;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocalityRepository extends CrudRepository<Locality, Long> {

    Locality findById(long id);
    Locality findByPostalCode(String postalCode);
    Locality findByLocality(String locality);




}