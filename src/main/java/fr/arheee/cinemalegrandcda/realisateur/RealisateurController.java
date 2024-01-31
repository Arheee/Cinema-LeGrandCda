package fr.arheee.cinemalegrandcda.realisateur;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.arheee.cinemalegrandcda.film.Film;
import fr.arheee.cinemalegrandcda.film.dto.FilmReduitDto;
import fr.arheee.cinemalegrandcda.film.dto.FilmSansActeursDto;
import fr.arheee.cinemalegrandcda.film.dto.FilmTresReduitDto;
import fr.arheee.cinemalegrandcda.realisateur.dto.RealisateurAvecFilmsDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/realisateurs")
public class RealisateurController {

    private final RealisateurService realisateurService;
    private final ObjectMapper objectMapper;


    public RealisateurController(RealisateurService realisateurService, ObjectMapper objectMapper) {
        this.realisateurService = realisateurService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<Realisateur> findAll() {
        return realisateurService.findAll();
    }

    @PostMapping
    public Realisateur save(@RequestBody Realisateur realisateur) {
        return realisateurService.save(realisateur);
    }

    @GetMapping("/{id}")
    public RealisateurAvecFilmsDto findById(@PathVariable Integer id) {
       return realisateurService.getFilmFromRealisateur(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        realisateurService.deleteById(id);
    }

    @PutMapping
    public Realisateur update(@RequestBody Realisateur realisateur) {
        return realisateurService.update(realisateur);
    }

    @GetMapping("/{id}/films")
    public List<FilmTresReduitDto> getFilmByRealisateurId(@PathVariable Integer id) {
        List<Film> films =  realisateurService.getFilmByRealisateurId(id);
        return films.stream().map(
                film -> objectMapper.convertValue(film, FilmTresReduitDto.class)
        ).toList();
    }
}
