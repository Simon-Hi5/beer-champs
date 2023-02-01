package at.ac.uibk.beerchamps.service;

import at.ac.uibk.beerchamps.persistence.Round;
import at.ac.uibk.beerchamps.persistence.Team;
import at.ac.uibk.beerchamps.persistence.Tournament;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TournamentService {
    void createTournament(Tournament tournament);

    void updateTournament(long id, Tournament tournament);

    void deleteTournament(long tournamentId);

    Tournament findTournament(long tournamentId);

    Tournament generateGames(Tournament tournament);

    void setWinner(Tournament tournament, long game_id, long winnerId);

    List<Team> generateScoreboard(Round round);

    List<Tournament> findAllTournaments();
}
