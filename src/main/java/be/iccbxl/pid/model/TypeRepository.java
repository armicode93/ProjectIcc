package be.iccbxl.pid.model;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TypeRepository extends CrudRepository <Type, Long> {

    Optional<Type> findById(Long id);
    Type findByType(String type);
}
