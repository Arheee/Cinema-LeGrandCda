package fr.arheee.cinemalegrandcda.seance;

import fr.arheee.cinemalegrandcda.salle.Salle;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SeanceService {
    private final SeanceRepository seanceRepository;

    public SeanceService(SeanceRepository seanceRepository) {
        this.seanceRepository = seanceRepository;
    }

    public Seance save(Seance seance){
        return seanceRepository.save(seance);
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
