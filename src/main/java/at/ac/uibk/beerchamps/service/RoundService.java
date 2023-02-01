package at.ac.uibk.beerchamps.service;

import at.ac.uibk.beerchamps.persistence.Round;
import org.springframework.stereotype.Component;

import java.util.List;

public interface RoundService {
    long createRound(Round round, long tournamentId);

    long updateRound(long id, Round round);

    long deleteRound(long roundId);

    Round findRound(long roundId);

    List<Round> findAllRoundsForTournament(long tournamentId);
}
