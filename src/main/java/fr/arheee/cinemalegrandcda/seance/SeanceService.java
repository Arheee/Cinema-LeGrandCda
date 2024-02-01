package fr.arheee.cinemalegrandcda.seance;

import fr.arheee.cinemalegrandcda.film.FilmService;
import fr.arheee.cinemalegrandcda.salle.Salle;
import fr.arheee.cinemalegrandcda.salle.SalleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class SeanceService {
    private final SeanceRepository seanceRepository;
    private final SalleService salleService;
    private final FilmService filmService;

    public SeanceService(SeanceRepository seanceRepository, SalleService salleService, FilmService filmService) {
        this.seanceRepository = seanceRepository;
        this.salleService = salleService;
        this.filmService = filmService;
    }

    public Seance save(Seance seance){
        salleService.findById(seance.getSalle().getId());
        filmService.findById(seance.getFilm().getId());

        verifySeance(seance);

        int capaciteSalle = salleService.findCapaciteById(seance.getSalle().getId());
        int placesDejaReservees = seanceRepository.countBySalleId(seance.getSalle().getId());

        if ( capaciteSalle -placesDejaReservees < 1){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La salle est pleine pour cette seance"
            );
        }
        seance.setPlaceDisponible(capaciteSalle - placesDejaReservees);
        return seanceRepository.save(seance);
    }

    private static void verifySeance(Seance seance) {
        //Verifie si la date est dans le futur
        if(seance.getDate().isBefore(LocalDate.now())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "On est pas dans Retour vers le futur"
            );
        }

        //Verifie que le prix est positif
        if(seance.getPrix() <= 0){
            throw  new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Le prix doit etre positif"
            );
        }
    }

    public List<Seance> findAll() {
        return seanceRepository.findAll();
    }

    public Seance findById(Integer id) {
        return seanceRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aucune séance avec cet Id"
                )
        );
    }

    public void deleteById(Integer id){
        Seance seance = this.findById(id);
        seanceRepository.delete(seance);
    }

    public Seance update(Seance seance){
        return seanceRepository.save(seance);
    }


}
