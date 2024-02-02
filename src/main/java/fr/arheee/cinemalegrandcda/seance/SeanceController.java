package fr.arheee.cinemalegrandcda.seance;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.arheee.cinemalegrandcda.film.Film;
import fr.arheee.cinemalegrandcda.realisateur.Realisateur;
import fr.arheee.cinemalegrandcda.seance.dto.FilmSeanceTestDto;
import fr.arheee.cinemalegrandcda.ticket.Ticket;
import fr.arheee.cinemalegrandcda.ticket.TicketService;
import fr.arheee.cinemalegrandcda.ticket.dto.TicketReduitDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/seances")
public class SeanceController {
    private final SeanceService seanceService;
    private final TicketService ticketService;
    private final ObjectMapper objectMapper;

    public SeanceController(SeanceService seanceService, TicketService ticketService, ObjectMapper objectMapper) {
        this.seanceService = seanceService;
        this.ticketService = ticketService;
        this.objectMapper = objectMapper;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Seance save(@RequestBody Seance seance) {
        return seanceService.save(seance);
    }

    @GetMapping
    public List<FilmSeanceTestDto> findAll() {
        List<Seance> seances = seanceService.findAll();
        return seances.stream().map(
                seance -> objectMapper.convertValue(seance, FilmSeanceTestDto.class)
        ).toList();
    }

    @GetMapping("/{id}")
    public FilmSeanceTestDto findById(@PathVariable Integer id) {
        Seance seance = seanceService.findById(id);
        return objectMapper.convertValue(seance,FilmSeanceTestDto.class);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        seanceService.deleteById(id);
    }
    @PutMapping
    public Seance update(@RequestBody Seance seance) {
        return seanceService.update(seance);
    }

    @PostMapping("/{id}/reserver")
    public Ticket getTicketForSeanceId(@PathVariable Integer id, @RequestBody Ticket ticket) {
        ticket.setSeance(new Seance());
        ticket.getSeance().setId(id);

        return ticketService.save(ticket);
    }

    @GetMapping("/{id}/tickets")
    public List<TicketReduitDto> getTicketsForSeanceId(@PathVariable Integer id) {
        Seance seance = seanceService.findById(id);
        List<Ticket> tickets = ticketService.findBySeance(seance);
        return tickets.stream().map(
                ticket -> objectMapper.convertValue(ticket, TicketReduitDto.class)
                ).toList();
    }

    @GetMapping("/disponible") //disponible?date=2025-10-01
    public List<FilmSeanceTestDto> getSeancesByDate(@RequestParam("date") String dateSeance) {
        LocalDate date = LocalDate.parse(dateSeance);
        List<Seance> seances = seanceService.findByDate(date);

        return seances.stream().map(
                seance -> objectMapper.convertValue(seance, FilmSeanceTestDto.class)
                ).toList();
    }

//    @GetMapping("/film/{id}/seances")
//    public List<FilmSeanceTestDto> getSeancesDisponiblesForFilm(@PathVariable Integer id) {
//        List<Seance> seances = seanceService.findSeancesDisponiblesForFilm(id);
//        return seances.stream().map(
//                        seance -> objectMapper.convertValue(seance, FilmSeanceTestDto.class)
//                ).toList();
//    }
}
