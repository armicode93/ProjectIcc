package be.iccbxl.pid.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="roles")

@ToString


public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @NotEmpty(message = "Role value is required and can t be empty")
    @Size(min=2, max=30, message ="Role should have at least 2 characters and max 30 characters")
    @Column(name = "role")
    private String roleName;

    @ManyToMany
    @JoinTable(
            name="role_user", //name table de jointure
            joinColumns= @JoinColumn(name="user_id", referencedColumnName = "id"), //name column de jointure
            inverseJoinColumns= @JoinColumn(
                    name = "role_id", referencedColumnName = "id")) //name deuxieme column de jointure
    //I added referencedColumnName
    private List<User> users= new ArrayList<>();

    protected Role() {

    }

    public Role(String roleName) {
        super();
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String role) {
        this.roleName = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

