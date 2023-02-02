package at.ac.uibk.beerchamps.service;

import at.ac.uibk.beerchamps.persistence.Game;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GameService {
    long createGame(Game game, long tournamentId);

    long updateGame(long id, Game game);

    long deleteGame(long gameId);

    Game findGame(long gameId);

    List<Game> findAllGamesForRound(long roundId);
}
