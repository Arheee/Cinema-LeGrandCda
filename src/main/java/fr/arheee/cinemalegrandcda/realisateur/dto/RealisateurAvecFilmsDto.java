package fr.arheee.cinemalegrandcda.realisateur.dto;

import fr.arheee.cinemalegrandcda.film.dto.FilmTresReduitDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RealisateurAvecFilmsDto {
    private String prenom;
    private String nom;

    private List<FilmTresReduitDto> films = new ArrayList<>();
}
