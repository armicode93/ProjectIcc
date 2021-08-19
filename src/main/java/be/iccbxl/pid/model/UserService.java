package be.iccbxl.pid.model;

import java.util.ArrayList;
import java.util.List;

import be.iccbxl.pid.controller.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService; //username - the username identifying the user whose data is required.
import org.springframework.stereotype.Service;
@Service
public interface UserService extends UserDetailsService {
   User save(UserRegistrationDto registrationDto);

    /*public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        repository.findAll().forEach(users::add);
        return users;
    }
    public User getUser(String id) {
        int indice = Integer.parseInt(id);
        return repository.findById(indice);
    }
    public void addUser(User user) {
        repository.save(user);
    }
    public void updateUser(String id, User user) {
        repository.save(user);
    }
    public void deleteUser(String id) {
        Long indice = (long) Integer.parseInt(id);
        repository.deleteById(indice);
    }

     */

}

