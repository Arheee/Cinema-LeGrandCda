package fr.arheee.cinemalegrandcda.film;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.arheee.cinemalegrandcda.film.dto.FilmCompletDto;
import fr.arheee.cinemalegrandcda.film.dto.FilmReduitDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;
    private final ObjectMapper objectMapper;

    public FilmController(FilmService filmService, ObjectMapper objectMapper) {
        this.filmService = filmService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    //delegateMethod , regarde les dependances et code
    public List<FilmReduitDto> findAll() {
        return filmService.findAll().stream().map(
                film -> objectMapper.convertValue(film, FilmReduitDto.class)
        ).toList();
    }

    @PostMapping
    public Film save(@RequestBody Film film) {
        return filmService.save(film);
    }

    @GetMapping("/{id}") //si on veut passer une ressource en particulier
    public FilmCompletDto findById(@PathVariable Integer id) {
        Film film =  filmService.findById(id);
        return objectMapper.convertValue(film, FilmCompletDto.class);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
         filmService.deleteById(id);
    }

    @PutMapping
    public Film update(@RequestBody Film film) {
        return filmService.update(film);
    }

    @GetMapping("/search") //films/search?titre=toto
    public Film findByTitre(@RequestParam String titre) {
        return filmService.findByTitre(titre);
    }
}

