package fr.arheee.cinemalegrandcda.film.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FilmReduitDto {

    private Integer id;
    private String titre;
    private LocalDate dateSortie;
    private int duree;
    private String synopsis;
}
/**
 * Ensuite on va dans le controller
 * cest plus simple que dans le service
 * si deux service qui communique entre eux alors zero problème
 */