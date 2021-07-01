package be.iccbxl.pid.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {

    List<Role> findByRoleName(String role);
    Role findById(long id);


}