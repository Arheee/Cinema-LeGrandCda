package fr.arheee.cinemalegrandcda.ticket;

import fr.arheee.cinemalegrandcda.seance.Seance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    List<Ticket> findBySeance(Seance seance);

}
