package at.ac.uibk.beerchamps.service;

import at.ac.uibk.beerchamps.persistence.Tournament;

import java.util.List;

public interface TournamentService {
    Tournament createTournament(Tournament tournament);

    Tournament editTournament(Tournament tournament);

    Tournament removeTournament(Tournament tournament);

    List<Tournament> getCurrentTournaments();
}
