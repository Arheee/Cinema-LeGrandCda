package fr.arheee.cinemalegrandcda.film;

import fr.arheee.cinemalegrandcda.acteur.Acteur;
import fr.arheee.cinemalegrandcda.acteur.dto.ActeurReduitDto;
import fr.arheee.cinemalegrandcda.acteur.dto.ActeurSansFilmDto;
import fr.arheee.cinemalegrandcda.realisateur.Realisateur;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class FilmService {
    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    //appel du service
    public List<Film> findAll(){
        return filmRepository.findAll();
    }

    public Film save(Film film) {
        return filmRepository.save(film);
    }

    public Film findById(Integer id) {
        return filmRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Film non trouvé")
        );
    }

    public void deleteById(Integer id) {
       Film film =  this.findById(id);
         filmRepository.delete(film);
    }

    public Film update(Film film) {

        return filmRepository.save(film);
    }

    public Film findByTitre(String titre) {
        return filmRepository.findByTitre(titre).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aucun films avec le titre : " + titre
                )
        );
    }


    public List<Film> findAllByRealisateurId(Integer id){
        return filmRepository.findAllByRealisateurId(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aucun film avec ce réalisateur"
                )
        );
    }

    public List<Acteur> getActeursByFilmId(Integer id) {
        Film film = findById(id);
        return film.getActeurs();
    }

    public Realisateur getRealisateurByFilmId(Integer id){
        Film film = findById(id);
        return  film.getRealisateur();
    }

    public List<Film> getFilmByRealisateurId(Integer id) {
        return filmRepository.getFilmByRealisateurId(id);
    }
}
