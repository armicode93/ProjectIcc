package be.iccbxl.pid.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@Entity
@Table(name="localities")
@NoArgsConstructor
@AllArgsConstructor
public class Locality {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String postalCode;
    private String locality;


}


