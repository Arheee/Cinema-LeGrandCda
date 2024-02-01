package fr.arheee.cinemalegrandcda.ticket;

import fr.arheee.cinemalegrandcda.salle.Salle;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket findById(Integer id) {
        return ticketRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Ce ticket est un faux"
                )
        );
    }

    public void deleteById(Integer id){
        Ticket ticket = this.findById(id);
        ticketRepository.delete(ticket);
    }

    public Ticket update(Ticket ticket){
        return ticketRepository.save(ticket);
    }
}
