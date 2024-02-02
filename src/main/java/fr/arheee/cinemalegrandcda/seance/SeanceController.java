package fr.arheee.cinemalegrandcda.seance;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.arheee.cinemalegrandcda.realisateur.Realisateur;
import fr.arheee.cinemalegrandcda.seance.dto.FilmSeanceTestDto;
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
}
