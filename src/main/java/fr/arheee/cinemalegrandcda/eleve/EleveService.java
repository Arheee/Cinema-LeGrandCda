package fr.arheee.cinemalegrandcda.eleve;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EleveService {

    private final EleveRepository eleveRepository;


    public EleveService(EleveRepository eleveRepository) {
        this.eleveRepository = eleveRepository;
    }

    public List<Eleve> findAll(){
        return eleveRepository.findAll();
    }

    public Eleve save(Eleve eleve){
        return  eleveRepository.save(eleve);
    }
}
