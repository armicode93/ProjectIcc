package be.iccbxl.pid.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="roles")
@NoArgsConstructor


public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String role;

    @ManyToMany
    @JoinTable(
            name="role_user", //name table de jointure
            joinColumns= @JoinColumn(name="user_id"), //name column de jointure
            inverseJoinColumns= @JoinColumn(name = "role_id")) //name deuxieme column de jointure
    private List<User> users= new ArrayList<>();


    public Role(String role) {
        super();
        this.role = role;
    }



}

