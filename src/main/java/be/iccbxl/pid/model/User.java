package be.iccbxl.pid.model;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users", uniqueConstraints = @UniqueConstraint(columnNames = "email")) //unique
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    private String login;

    private String password;

    private String firstname;

    private String lastname;

    private String email;

    @Size(min=2, max=2)

    private String langue;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy="users") //caricamento veloce dei dati,senno LAZY caricamento quando necessario
    private Collection<Role> roles;

    @ManyToMany(mappedBy="users")
    private List <Representation> representations =new ArrayList<>();
    //Collection e linterfaccia principale della gerarchia di java Collection,che comprende List,SET


    public User(){}

    public User( String login, String password, String firstname, String lastname, String email, String langue, Collection<Role> roles) {
        super();
        this.login = login;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.langue = langue;
        this.roles = roles;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public List<Representation> getRepresentations() {
        return representations;
    }
    public User addRepresentation(Representation representation) {
        if(!this.representations.contains(representation)) {
            this.representations.add(representation);
            representation.addUser(this);
        }
        return this;
    }
    public User removeRepresentation(Representation representation) {
        if(this.representations.contains(representation)) {
            this.representations.remove(representation);
            representation.getUsers().remove(this);
        }
        return this;
    }
}
