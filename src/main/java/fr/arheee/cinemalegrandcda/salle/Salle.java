package fr.arheee.cinemalegrandcda.salle;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "salle")
public class Salle {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 500)
    private String nom;

    private int numero;
    private int capacite;


}
