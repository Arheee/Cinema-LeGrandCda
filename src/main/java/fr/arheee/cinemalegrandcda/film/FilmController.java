package fr.arheee.cinemalegrandcda.film;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.arheee.cinemalegrandcda.acteur.Acteur;
import fr.arheee.cinemalegrandcda.acteur.dto.ActeurSansFilmDto;
import fr.arheee.cinemalegrandcda.film.dto.FilmCompletDto;
import fr.arheee.cinemalegrandcda.film.dto.FilmReduitDto;
import fr.arheee.cinemalegrandcda.realisateur.Realisateur;
import org.apache.coyote.BadRequestException;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED) //201 ca sest bien passé , cest legerement différent de 200
    public Film save(@RequestBody Film film) throws BadRequestException {
        return filmService.save(film);
    }

    @GetMapping("/{id}") //si on veut passer une ressource en particulier
    public FilmCompletDto findById(@PathVariable Integer id) {
        Film film =  filmService.findById(id);
        FilmCompletDto filmCompletDto = new FilmCompletDto();
        filmCompletDto.setId(film.getId());
        filmCompletDto.setDuree(film.getDuree());
        filmCompletDto.setSynopsis(film.getSynopsis());
        filmCompletDto.setRealisateur(film.getRealisateur());
        filmCompletDto.setDateSortie(film.getDateSortie());
        filmCompletDto.setActeurs(
                film.getActeurs().stream().map(
                        acteur -> objectMapper.convertValue(acteur, ActeurSansFilmDto.class)
                ).toList()
        );


        return filmCompletDto;
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

    @GetMapping("/{id}/acteurs")
    public List<ActeurSansFilmDto> getActeursByFilmId(@PathVariable Integer id) {
        List<Acteur> acteurs = filmService.getActeursByFilmId(id);
        return acteurs.stream().map(
                acteur -> objectMapper.convertValue(acteur, ActeurSansFilmDto.class)
        ).toList();

    }

    @GetMapping("/{id}/realisateur")
    public Realisateur getRealisateurByFilmId(@PathVariable Integer id) {
       Realisateur realisateur = filmService.getRealisateurByFilmId(id);
       return objectMapper.convertValue(realisateur, Realisateur.class);
    }

    @PostMapping("/{id}/acteurs")
    public FilmCompletDto addActeurToFilm(@PathVariable Integer id,@RequestBody Acteur acteur) {
        Film film = filmService.addActeurToFilm(id, acteur);

        FilmCompletDto filmCompletDto = new FilmCompletDto();
        filmCompletDto.setId(film.getId());
        filmCompletDto.setDuree(film.getDuree());
        filmCompletDto.setRealisateur(film.getRealisateur());
        filmCompletDto.setDateSortie(film.getDateSortie());
        filmCompletDto.setSynopsis(film.getSynopsis());
        filmCompletDto.setActeurs(
                film.getActeurs().stream().map(
                        unmappedActor -> objectMapper.convertValue(
                                unmappedActor,
                                ActeurSansFilmDto.class
                )).toList()
        );

         return filmCompletDto;
    }
}

