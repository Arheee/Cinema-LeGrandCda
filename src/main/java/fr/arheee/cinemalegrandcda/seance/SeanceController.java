package fr.arheee.cinemalegrandcda.seance;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seances")
public class SeanceController {
    private final SeanceService seanceService;

    private final ObjectMapper objectMapper;

    public SeanceController(SeanceService seanceService, ObjectMapper objectMapper) {
        this.seanceService = seanceService;
        this.objectMapper = objectMapper;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Seance save(@RequestBody Seance seance) {
        return seanceService.save(seance);
    }

    @GetMapping
    public List<Seance> findAll() {
        return seanceService.findAll();
    }

    @GetMapping("/{id}")
    public Seance findById(@PathVariable Integer id) {
        return seanceService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        seanceService.deleteById(id);
    }
    @PutMapping
    public Seance update(@RequestBody Seance seance) {
        return seanceService.update(seance);
    }
}
