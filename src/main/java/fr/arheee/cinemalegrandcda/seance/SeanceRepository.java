package fr.arheee.cinemalegrandcda.seance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SeanceRepository extends JpaRepository<Seance, Integer> {
    int countBySalleId(Integer salleId);
}
