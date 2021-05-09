package be.iccbxl.pid.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@Entity
@Table(name="roles")
@NoArgsConstructor


public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String role;


    public Role(String role) {
        super();
        this.role = role;
    }



}

