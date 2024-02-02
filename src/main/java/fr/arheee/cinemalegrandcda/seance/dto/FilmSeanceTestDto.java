package fr.arheee.cinemalegrandcda.seance.dto;

import fr.arheee.cinemalegrandcda.film.dto.FilmSeanceDto;
import fr.arheee.cinemalegrandcda.salle.Salle;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FilmSeanceTestDto {
    private Integer id;
    private FilmSeanceDto film;
    private Salle salle;
    private LocalDate date;
    private int placeDisponible;
    private float prix;
}
