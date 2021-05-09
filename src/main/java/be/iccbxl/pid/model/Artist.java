package be.iccbxl.pid.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data //no setter no getter ,with this annotation is inside
@Entity
@Table(name="artists")
@AllArgsConstructor
@NoArgsConstructor
public class Artist {
    @Id //clef primaire, identifiant unique
    @GeneratedValue(strategy=GenerationType.AUTO) //auto generate
    private Long id;
    private String firstname;
    private String lastname;


}
