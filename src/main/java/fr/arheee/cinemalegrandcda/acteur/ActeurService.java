package fr.arheee.cinemalegrandcda.acteur;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ActeurService {
    private final ActeurRepository acteurRepository;

    public ActeurService(ActeurRepository acteurRepository) {
        this.acteurRepository = acteurRepository;
    }

    public List<Acteur> findAll() {
        return acteurRepository.findAll();
    }

    public Acteur save(Acteur acteur) {
        return acteurRepository.save(acteur);
    }

    public Acteur findById(Integer id) {
        return acteurRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Acteur non trouvé")
        );
    }

    public void deleteById(Integer id) {
        Acteur acteur = this.findById(id);
        acteurRepository.delete(acteur);
    }

    public Acteur update(Acteur acteur) {
        return acteurRepository.save(acteur);
    }



}
