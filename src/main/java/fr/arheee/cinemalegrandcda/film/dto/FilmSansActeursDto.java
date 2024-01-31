package fr.arheee.cinemalegrandcda.film.dto;

import fr.arheee.cinemalegrandcda.realisateur.Realisateur;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FilmSansActeursDto {
    private Integer id;
    private String titre;
    private LocalDate dateSortie;
    private int duree;
    private String synopsis;
    private Realisateur realisateur;
}
