package fr.arheee.cinemalegrandcda.acteur;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.arheee.cinemalegrandcda.acteur.dto.ActeurReduitDto;
import fr.arheee.cinemalegrandcda.acteur.dto.ActeurSansFilmDto;
import fr.arheee.cinemalegrandcda.film.Film;
import fr.arheee.cinemalegrandcda.film.dto.FilmCompletDto;
import fr.arheee.cinemalegrandcda.film.dto.FilmReduitDto;
import fr.arheee.cinemalegrandcda.film.dto.FilmSansActeursDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acteurs")
public class ActeurController {
    private final ActeurService acteurService;
    private final ObjectMapper objectMapper;


    public ActeurController(ActeurService acteurService, ObjectMapper objectMapper) {
        this.acteurService = acteurService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<ActeurSansFilmDto> findAll() {
        return acteurService.findAll().stream().map(
                acteur -> objectMapper.convertValue(acteur, ActeurSansFilmDto.class)
                ).toList();
    }
    @PostMapping
    public Acteur save(@RequestBody Acteur acteur) {
        return acteurService.save(acteur);
    }

    @GetMapping("/{id}")
    public ActeurReduitDto findById(@PathVariable Integer id) {
        Acteur acteur =  acteurService.findById(id);
        return objectMapper.convertValue(acteur, ActeurReduitDto.class);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        acteurService.deleteById(id);
    }

    @PutMapping
    public Acteur update(@RequestBody Acteur acteur) {
        return acteurService.update(acteur);
    }


}
