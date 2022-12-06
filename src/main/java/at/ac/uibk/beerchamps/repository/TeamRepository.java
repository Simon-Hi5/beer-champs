package at.ac.uibk.beerchamps.repository;

import at.ac.uibk.beerchamps.persistence.Team;
import at.ac.uibk.beerchamps.persistence.Tournament;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Set<Team> findByTournament(Tournament tournament, Sort sort);
}
