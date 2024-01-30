package fr.arheee.cinemalegrandcda.realisateur;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/realisateurs")
public class RealisateurController {

    private final RealisateurService realisateurService;


    public RealisateurController(RealisateurService realisateurService) {
        this.realisateurService = realisateurService;
    }

    @GetMapping
    public List<Realisateur> findAll() {
        return realisateurService.findAll();
    }

    @PostMapping
    public Realisateur save(@RequestBody Realisateur realisateur) {
        return realisateurService.save(realisateur);
    }

    @GetMapping("/{id}")
    public Realisateur findById(@PathVariable Integer id) {
        return realisateurService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        realisateurService.deleteById(id);
    }

    @PutMapping
    public Realisateur update(@RequestBody Realisateur realisateur) {
        return realisateurService.update(realisateur);
    }
}
