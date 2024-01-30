package fr.arheee.cinemalegrandcda.eleve;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eleves")
public class EleveController {

    private final EleveService eleveService;


    public EleveController(EleveService eleveService) {
        this.eleveService = eleveService;
    }

    @GetMapping
    public List<Eleve> findAll() {
        return eleveService.findAll();
    }

    @PostMapping
    public Eleve save(@RequestBody Eleve eleve) {
        return eleveService.save(eleve);
    }
}
