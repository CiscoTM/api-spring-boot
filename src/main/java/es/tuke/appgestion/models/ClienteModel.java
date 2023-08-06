package es.tuke.appgestion.models;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "table_clients")
public class ClienteModel implements Serializable {
    
    @Id
    @Column(name="id_cliente")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="username")
    private String name;

    private String pass;
    
    @Column(name = "data_base")
    private String dataBase;

    private int active;


    
}