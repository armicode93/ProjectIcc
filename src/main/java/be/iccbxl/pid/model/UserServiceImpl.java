package be.iccbxl.pid.model;

import be.iccbxl.pid.controller.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{



    private UserRepository userRepository; //base injection

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto)
    {
        User user = new User(registrationDto.getLogin(),passwordEncoder.encode(registrationDto.getPassword()),
                registrationDto.getFirstname(),registrationDto.getLastname(),
                registrationDto.getEmail(),registrationDto.getLangue(),Arrays.asList(new Role("ROLE_USER")));
        //create a user object here
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByLogin(username); //prendiamo lo user object from the database //from the login screen we pass email adresse and password
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));

    }
    //Methode to maps roles to authorities, we are going to convert Roles t oauthorities
    //becouse spring security expecting authorities
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        //here im going to map  Roles to authorities

        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    //we converted roles to stream,w maped a roles and we converted role into s.grantedAuthority, is the spring security provided class
        //and after that we converted stream to a list
    }
}


