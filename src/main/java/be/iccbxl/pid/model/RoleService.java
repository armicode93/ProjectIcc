package be.iccbxl.pid.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private  RoleRepository roleRepository;

    public List<Role> getAllRole(){
        List <Role> roles = new ArrayList<>();

        roleRepository.findAll().forEach(roles::add);

        return roles;
    }

    public Role getRole(String id)
    {
        int indice = Integer.parseInt(id);

        return roleRepository.findById(indice);

    }
    public void add(Role role)
    {
        roleRepository.save(role);
    }

    public void update(String id,Role role){
        roleRepository.save(role);
    }

    public void delete(Long id)
    {

        roleRepository.deleteById(id);
    }
}
