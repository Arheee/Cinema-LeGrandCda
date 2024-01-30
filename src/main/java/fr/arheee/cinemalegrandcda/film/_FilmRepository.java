package fr.arheee.cinemalegrandcda.film;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional // test avant de commencer voir si ca fonctionne avant d'aller en bdd
public class _FilmRepository {

    private final EntityManager entityManager;

    public _FilmRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Film> findAll(){
        return entityManager.createQuery("SELECT f FROM Film f", Film.class).getResultList();
    }

    public Film save(Film film){
        entityManager.persist(film);
        entityManager.flush(); //valide l'entree
        return film;
    }

    // Optional return null ou return le Film
    public Optional<Film> findById(Integer id){
        return Optional.ofNullable(entityManager.find(Film.class, id));
    }

    public void deleteById(Film film ){
        entityManager.remove(film);
    }

    public Film update(Film film){
        return  entityManager.merge(film);
        //merge ecrase et remplace
    }
}
