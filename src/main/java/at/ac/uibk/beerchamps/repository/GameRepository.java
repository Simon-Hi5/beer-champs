package at.ac.uibk.beerchamps.repository;

import at.ac.uibk.beerchamps.persistence.Game;
import at.ac.uibk.beerchamps.persistence.Round;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findAllByRound(Round round);
}
