package fr.arheee.cinemalegrandcda.eleve;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Eleve {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL) //crud cascade : permet d'envoyer des objets imbriqué
    private CarnetDeNote carnetDeNote;
}
