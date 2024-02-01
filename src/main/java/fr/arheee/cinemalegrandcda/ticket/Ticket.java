package fr.arheee.cinemalegrandcda.ticket;

import fr.arheee.cinemalegrandcda.seance.Seance;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "seance_id")
    private Seance seance;

    private String nomClient;
    private int nombrePlaces;
}

