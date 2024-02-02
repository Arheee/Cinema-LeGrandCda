package fr.arheee.cinemalegrandcda.ticket;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.arheee.cinemalegrandcda.seance.SeanceService;
import fr.arheee.cinemalegrandcda.ticket.exceptions.SeanceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;
    private final ObjectMapper objectMapper;
    private final SeanceService seanceService;
    
    public TicketController(TicketService ticketService, ObjectMapper objectMapper, SeanceService seanceService) {
        this.ticketService = ticketService;
        this.objectMapper = objectMapper;
        this.seanceService = seanceService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket save(@RequestBody Ticket ticket) {
        return ticketService.save(ticket);
    }
    @GetMapping
    public List<Ticket> findAll() {
        return ticketService.findAll();
    }
    @GetMapping("/{id}")
    public Ticket findById(@PathVariable Integer id) {
        return ticketService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        ticketService.deleteById(id);
    }

    @PutMapping
    public Ticket update(@RequestBody Ticket ticket) {
        return ticketService.update(ticket);
    }
}
