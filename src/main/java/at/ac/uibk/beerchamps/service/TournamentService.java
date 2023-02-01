package at.ac.uibk.beerchamps.service;

import at.ac.uibk.beerchamps.persistence.Game;
import at.ac.uibk.beerchamps.persistence.Round;
import at.ac.uibk.beerchamps.persistence.Team;
import at.ac.uibk.beerchamps.persistence.Tournament;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public interface TournamentService {
    long createTournament(Tournament tournament);

    long updateTournament(long id, Tournament tournament);

    long deleteTournament(long tournamentId);

    Tournament findTournament(long tournamentId);

    Tournament generateGames(Tournament tournament);

    void setWinner (Tournament tournament, Game game, Team winner);

    List<Team> generateScoreboard(Round round);

    List<Tournament> findAllTournaments();
}
