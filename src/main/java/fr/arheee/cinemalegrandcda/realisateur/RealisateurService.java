package fr.arheee.cinemalegrandcda.realisateur;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.arheee.cinemalegrandcda.film.Film;
import fr.arheee.cinemalegrandcda.film.FilmRepository;
import fr.arheee.cinemalegrandcda.film.FilmService;
import fr.arheee.cinemalegrandcda.film.dto.FilmReduitDto;
import fr.arheee.cinemalegrandcda.film.dto.FilmTresReduitDto;
import fr.arheee.cinemalegrandcda.realisateur.dto.RealisateurAvecFilmsDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RealisateurService {
    private final RealisateurRepository realisateurRepository;
    private  final FilmService filmService;

    private final ObjectMapper objectMapper;


    public RealisateurService(RealisateurRepository realisateurRepository, FilmService filmService, ObjectMapper objectMapper) {
        this.realisateurRepository = realisateurRepository;
        this.filmService = filmService;
        this.objectMapper = objectMapper;
    }

    public List<Realisateur> findAll(){
        return realisateurRepository.findAll();
    }

    public Realisateur save(Realisateur realisateur){
        return realisateurRepository.save(realisateur);
    }

    public Realisateur findById(Integer id){
        return realisateurRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Réalisateur non trouvé")
        );
    }

    public void deleteById(Integer id){
        Realisateur realisateur = this.findById(id);

        List<Film> filmAvecRea =  filmService.findAllByRealisateurId(id);
        filmAvecRea.forEach(
                film -> {
                    film.setRealisateur(null);
                    filmService.save(film);
                }
        );

        realisateurRepository.delete(realisateur);
    }

    public Realisateur update(Realisateur realisateur){
        return realisateurRepository.save(realisateur);
    }


    public List<Film> getFilmByRealisateurId(Integer id) {
        return filmService.getFilmByRealisateurId(id);
    }

    public RealisateurAvecFilmsDto getFilmFromRealisateur(Integer id){
        Realisateur realisateur =  findById(id);
        List<FilmTresReduitDto> films = getFilmByRealisateurId(id).stream().map(
                film -> objectMapper.convertValue(film, FilmTresReduitDto.class)
        ).toList();
        RealisateurAvecFilmsDto realisateurAvecFilmsDto = new RealisateurAvecFilmsDto();
        realisateurAvecFilmsDto.setFilms(films);
        realisateurAvecFilmsDto.setPrenom(realisateur.getPrenom());
        realisateurAvecFilmsDto.setNom(realisateur.getNom());
        return realisateurAvecFilmsDto;
    }
}
