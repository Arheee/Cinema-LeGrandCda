package fr.arheee.cinemalegrandcda.ticket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
