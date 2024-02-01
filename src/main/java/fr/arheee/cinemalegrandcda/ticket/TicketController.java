package fr.arheee.cinemalegrandcda.ticket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;
    private final ObjectMapper objectMapper;
    
    public TicketController(TicketService ticketService, ObjectMapper objectMapper) {
        this.ticketService = ticketService;
        this.objectMapper = objectMapper;
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
