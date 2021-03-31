package model;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity //permet de definre la classe comme entite, un modele mappe avec un source de donn{es
@Table(name="artists")
public class Artist {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;

    protected Artist() {}

    public Artist (String firstname, String lastname)
    {
        this.firstname=firstname;
        this.lastname=lastname;
    }

 }
