package application.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="types")

public class Type {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)


    private Long id;
    private String type;

    public Type(){};

}
