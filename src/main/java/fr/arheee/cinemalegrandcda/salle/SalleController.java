package fr.arheee.cinemalegrandcda.salle;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.arheee.cinemalegrandcda.film.dto.FilmReduitDto;
import fr.arheee.cinemalegrandcda.salle.dto.SalleReduitDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salles")
public class SalleController {
    private final SalleService salleService;
    private final ObjectMapper objectMapper;

    public SalleController(SalleService salleService, ObjectMapper objectMapper) {
        this.salleService = salleService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Salle save(@RequestBody Salle salle) {
        return salleService.save(salle);
    }

    @GetMapping
    public List<Salle> findAll() {
        return salleService.findAll();
    }

    @GetMapping("/{id}")
    public SalleReduitDto findById(@PathVariable Integer id) {
        Salle salle = salleService.findById(id);
        SalleReduitDto salleReduitDto = new SalleReduitDto();
        salleReduitDto.setNom(salle.getNom());
        salleReduitDto.setCapacite(salle.getCapacite());
        return salleReduitDto;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        salleService.deleteById(id);
    }

    @PutMapping
    public Salle update(@RequestBody Salle salle) {
        return salleService.update(salle);
    }
}
