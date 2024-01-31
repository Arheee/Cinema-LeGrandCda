package fr.arheee.cinemalegrandcda.film.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FilmTresReduitDto {
    private String titre;
    private LocalDate dateSortie;
}
