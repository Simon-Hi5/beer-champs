package at.ac.uibk.beerchamps.service;

import at.ac.uibk.beerchamps.persistence.Game;
import at.ac.uibk.beerchamps.persistence.Round;
import at.ac.uibk.beerchamps.persistence.Team;
import at.ac.uibk.beerchamps.persistence.Tournament;
import at.ac.uibk.beerchamps.repository.GameRepository;
import at.ac.uibk.beerchamps.repository.RoundRepository;
import at.ac.uibk.beerchamps.repository.TeamRepository;
import at.ac.uibk.beerchamps.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Component
public class TournamentServiceImpl implements TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TeamRepository teamRepository;

    public void createTournament(Tournament tournament) {
        tournamentRepository.save(tournament);
    }

    public void updateTournament(long tournamentId, Tournament newTournament) {
        findTournament(tournamentId);
        tournamentRepository.save(newTournament);
    }

    public void deleteTournament(long tournamentId) {
        Tournament tournament = findTournament(tournamentId);
        tournamentRepository.delete(tournament);
    }

    public Tournament findTournament(long tournamentId) {
        Optional<Tournament> tournament = tournamentRepository.findById(tournamentId);
        if (tournament.isEmpty()) {
            throw new EntityNotFoundException("Tournament with ID=" + tournamentId + " not found.");
        }
        return tournament.get();
    }

    public List<Tournament> findAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Tournament generateGames(Tournament tournament) {
        List<Team> teams = tournament.getTeams();
        List<Game> games = new ArrayList<>();
        Round round = new Round();
        round.setGames(games);
        tournament.addRound(round);
        round.setTournament(tournament);
        roundRepository.save(round);
        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                Game game = new Game(teams.get(i), teams.get(j));
                game.setRound(round);
                games.add(game);
                gameRepository.save(game);
            }
        }

        updateTournament(tournament.getId(), tournament);
        return tournament;
    }

    public List<Team> generateScoreboard(Round round) {
        List<Team> scoreboard = new ArrayList<>();
        for (Game game : round.getGames()) {
            scoreboard.add(game.getWinner());
        }

        Map<Team, Integer> countMap = new HashMap<>();
        for (Team team : scoreboard) {
            countMap.put(team, countMap.getOrDefault(team, 0) + 1);
        }

        List<Team> orderedScoreBoard = new ArrayList<>(countMap.keySet());
        orderedScoreBoard.sort((o1, o2) -> countMap.get(o2) - countMap.get(o1));

        return orderedScoreBoard;
    }

    public void setWinner(Tournament tournament, long gameId, long winnerId) {
        List<Game> games = findTournament(tournament.getId()).getLastRound().getGames();
        Optional<Team> team = teamRepository.findById(winnerId);
        if (team.isPresent()) {
            Team winner = team.get();
            for (Game g : games) {
                if (g.getId() == gameId) {
                    g.setWinner(winner);
                    gameRepository.save(g);
                }
            }
        }
        tournament.getLastRound().setGames(games);
        updateTournament(tournament.getId(), tournament);
    }
}
