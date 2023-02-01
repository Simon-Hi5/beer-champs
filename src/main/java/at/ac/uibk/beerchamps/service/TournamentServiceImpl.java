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

        System.out.println(tournament.getRounds().size());
        updateTournament(tournament.getId(), tournament);
        return tournament;
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

    public void setWinner (Tournament tournament, long game_id, long winnerId) {
        List<Game> games = findTournament(tournament.getId()).getLastRound().getGames();
        Team winner = teamRepository.findById(winnerId).get();
        for (Game g:games) {
            if(g.getId() == game_id){
                g.setWinner(winner);
            }
        }
        tournament.getLastRound().setGames(games);
        updateTournament(tournament.getId(), tournament);
    }
}
