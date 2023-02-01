package at.ac.uibk.beerchamps.service;

import at.ac.uibk.beerchamps.persistence.Round;
import at.ac.uibk.beerchamps.persistence.Team;
import at.ac.uibk.beerchamps.persistence.Tournament;

import java.util.List;

public interface TournamentService {
    long createTournament(Tournament tournament);

    long updateTournament(long id, Tournament tournament);

    long deleteTournament(long tournamentId);

    Tournament findTournament(long tournamentId);

    Round generateGames(Tournament tournament);

    List<Tournament> findAllTournaments();
}
