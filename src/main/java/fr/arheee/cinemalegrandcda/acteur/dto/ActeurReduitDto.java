package fr.arheee.cinemalegrandcda.acteur.dto;

import fr.arheee.cinemalegrandcda.film.Film;
import fr.arheee.cinemalegrandcda.film.dto.FilmSansActeursDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ActeurReduitDto {
    private Integer id;
    private String nom;
    private String prenom;
    private List<FilmSansActeursDto> films = new ArrayList<>();
}
