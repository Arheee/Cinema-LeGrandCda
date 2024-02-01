package fr.arheee.cinemalegrandcda.salle;

import fr.arheee.cinemalegrandcda.film.Film;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SalleService {
    private final SalleRepository salleRepository;

    public SalleService(SalleRepository salleRepository) {
        this.salleRepository = salleRepository;
    }

    public Salle save(Salle salle) {
        return salleRepository.save(salle);
    }

    public List<Salle> findAll() {
        return salleRepository.findAll();
    }

    public Salle findById(Integer id) {
        return salleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aucune Salle avec cet Id"
                )
        );
    }

    public void deleteById(Integer id){
        Salle salle = this.findById(id);
        salleRepository.delete(salle);
    }

    public Salle update(Salle salle){
        return salleRepository.save(salle);
    }

    public int findCapaciteById(Integer id) {
        Salle salle = salleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aucune salle avec cet ID"
                )
        );
        return salle.getCapacite();
    }
}
