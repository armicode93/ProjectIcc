package application.demo.model;

import lombok.Data;

import javax.persistence.*;
@Data //no setter no getter ,with lombook
@Entity //permet de definre la classe comme entite, un modele mappe avec un source de donn{es, chaque ligne de code,corrisponde a un champs de la table
@Table(name="artists")
public class Artist {
    @Id //clef primaire, identifiant unique
    @GeneratedValue(strategy=GenerationType.AUTO) //auto generate
    private Long id;
    private String firstname;
    private String lastname;


 }
