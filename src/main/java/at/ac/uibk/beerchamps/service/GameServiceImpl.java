package at.ac.uibk.beerchamps.service;

import at.ac.uibk.beerchamps.persistence.Game;
import at.ac.uibk.beerchamps.persistence.Round;
import at.ac.uibk.beerchamps.persistence.Tournament;
import at.ac.uibk.beerchamps.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Component
public class GameServiceImpl implements GameService{

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private RoundService roundService;


    public long createGame(Game game, long roundId) {
        Round round = roundService.findRound(roundId);
        game.setRound(round);
        gameRepository.save(game);
        return game.getId();
    }

    @Override
    public long updateGame(long gameId, Game newGame) {
        findGame(gameId);
        gameRepository.save(newGame);
        return gameId;
    }

    @Override
    public long deleteGame(long gameId) {
        Game game = findGame(gameId);
        gameRepository.delete(game);
        return gameId;
    }

    @Override
    public Game findGame(long gameId) {
        Optional<Game> game = gameRepository.findById(gameId);
        if (game.isEmpty()) {
            throw new EntityNotFoundException("Team with ID=" + gameId + " not found.");
        }
        return game.get();
    }

    @Override
    public List<Game> findAllGamesForRound(long roundId) {
        Round round = roundService.findRound(roundId);
        return gameRepository.findAllByRound(round);
    }
}
