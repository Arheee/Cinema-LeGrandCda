package fr.arheee.cinemalegrandcda.film.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FilmNotFoundException extends RuntimeException{ //RuntimeException est erreur type classique


    public FilmNotFoundException( Integer idDuFilm){
        super("Film non trouvé avec l'ID " + idDuFilm);
    }

}
