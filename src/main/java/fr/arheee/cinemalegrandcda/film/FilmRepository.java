package fr.arheee.cinemalegrandcda.film;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Integer> {
    Optional<Film> findByTitre(String titre);

    Optional<Film> findByDateSortieAfter(LocalDate date);

    // va faire une requete du genre SELECT * FROM film WHERE rea_id ...
    Optional<List<Film>> findAllByRealisateurId(Integer id);
}
