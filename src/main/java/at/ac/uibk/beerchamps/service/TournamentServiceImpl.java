package at.ac.uibk.beerchamps.service;

import at.ac.uibk.beerchamps.persistence.Game;
import at.ac.uibk.beerchamps.persistence.Round;
import at.ac.uibk.beerchamps.persistence.Team;
import at.ac.uibk.beerchamps.persistence.Tournament;
import at.ac.uibk.beerchamps.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Component
public class TournamentServiceImpl implements TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    public long createTournament(Tournament tournament) {
        tournamentRepository.save(tournament);
        return tournament.getId();
    }

    public long updateTournament(long tournamentId, Tournament newTournament) {
        findTournament(tournamentId);
        tournamentRepository.save(newTournament);
        return tournamentId;
    }

    public long deleteTournament(long tournamentId) {
        Tournament tournament = findTournament(tournamentId);
        tournamentRepository.delete(tournament);
        return tournamentId;
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

    public Round generateGames(Tournament tournament) {
        List<Team> teams = tournament.getTeams();
        List<Game> games = new ArrayList<>();
        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                games.add(new Game(teams.get(i), teams.get(j)));
            }
        }
        Round round = new Round();
        round.setGames(games);
        tournament.addRound(round);
        updateTournament(tournament.getId(), tournament);
        return round;
    }

    public List<Team> generateScoreboard(Round round) {
        List<Team> scoreboard = new ArrayList<>();
        for (Game game:round.getGames()) {
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

    public void setWinner (Tournament tournament, Game game, Team winner) {
    List<Game> games = findTournament(tournament.getId()).getLastRound().getGames();
    for (Game g:games) {
        if(g.getId() == game.getId()){
            game.setWinner(winner);
        }
    }
    tournament.getLastRound().setGames(games);
    updateTournament(tournament.getId(), tournament);
    }
}
