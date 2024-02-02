package fr.arheee.cinemalegrandcda.seance;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SeanceRepository extends JpaRepository<Seance, Integer> {
    int countBySalleId(Integer salleId);
    List<Seance> findByDate(LocalDate date);


}
