package application.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;




import javax.persistence.*;

@Data
@Entity
@Table(name="localities")

public class Locality {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String postalCode;
    private String locality ;

    public Locality(){ }
}
