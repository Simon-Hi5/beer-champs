package at.ac.uibk.beerchamps.repository;

import at.ac.uibk.beerchamps.persistence.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

}