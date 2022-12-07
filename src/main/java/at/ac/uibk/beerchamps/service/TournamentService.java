package at.ac.uibk.beerchamps.service;

import at.ac.uibk.beerchamps.persistence.Tournament;

import java.util.List;

public interface TournamentService {
    long createTournament(Tournament tournament);

    long updateTournament(long id, Tournament tournament);

    long deleteTournament(long tournamentId);

    Tournament findTournament(long tournamentId);

    List<Tournament> findAllTournaments();
}
