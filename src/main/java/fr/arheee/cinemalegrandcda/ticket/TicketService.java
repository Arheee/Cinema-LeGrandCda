package fr.arheee.cinemalegrandcda.ticket;

import fr.arheee.cinemalegrandcda.film.exceptions.FilmNotFoundException;
import fr.arheee.cinemalegrandcda.seance.Seance;
import fr.arheee.cinemalegrandcda.seance.SeanceService;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;



@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    private final SeanceService seanceService;

    public TicketService(TicketRepository ticketRepository, SeanceService seanceService) {
        this.ticketRepository = ticketRepository;
        this.seanceService = seanceService;
    }

    public Ticket save(Ticket ticket) {
        Integer seanceId = ticket.getSeance().getId();
        Seance seance = seanceService.findById(seanceId);
        if( seance == null ){
               throw new ResponseStatusException(
                       HttpStatus.NOT_FOUND,

                "l'ID de cette séance n'existe pas ");
        }
        int placeAchetees = ticket.getNombrePlaces();

        if (placeAchetees <= 0
                || placeAchetees > seance.getPlaceDisponible()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "On lui donne le doigt et il vous prend le bras, " +
                            " TU PEUX PAS OK "
            );
        }

     if(ticket.getNomClient() == null || ticket.getNomClient().isEmpty()){
         throw new ResponseStatusException(
                 HttpStatus.BAD_REQUEST,
                 "Ton nom qui pue la stp, tu le met."
         );
     }

     // Met à jour le nombre de places disponibles dans la séance
        seance.setPlaceDisponible(seance.getPlaceDisponible() - placeAchetees);
        seanceService.update(seance);

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
